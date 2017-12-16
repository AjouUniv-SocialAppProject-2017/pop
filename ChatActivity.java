package com.example.kyu.sap;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.example.kyu.sap.DetailActivity.chatroom;

/**
 * Created by SAMSUNG on 2017-11-17.
 */
public class ChatActivity extends AppCompatActivity {
    private ImageView btn_send;
    private EditText et_msg;
    private ListView lv_chating;
    private TextView chatroom_title;
    private ArrayAdapter<String> arrayAdapter;

    private String str_name="황선욱";
    private String str_msg;
    private String chat_user;
    private DatabaseReference reference = FirebaseDatabase.getInstance()
            .getReference().child("message").child(chatroom);
    public static ArrayList<Data> item_list = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.chatroom);

        lv_chating = (ListView) findViewById(R.id.lv_chating);
        btn_send = (ImageView) findViewById(R.id.btn_send);
        et_msg = (EditText) findViewById(R.id.et_msg);
        chatroom_title=(TextView)findViewById(R.id.chatroom_title);
        chatroom_title.setText(chatroom);
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        lv_chating.setAdapter(arrayAdapter);

        // 리스트뷰가 갱신될때 하단으로 자동 스크롤
        lv_chating.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

//        str_name = "Guest " + new Random().nextInt(1000)

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {
//                Toast.makeText(getApplicationContext(), intent.getExtras().getString("pj_name"), Toast.LENGTH_SHORT).show();
                // map을 사용해 name과 메시지를 가져오고, key에 값 요청
                Map<String, Object> map = new HashMap<String, Object>();

                // key로 데이터베이스 오픈
                String key = reference.push().getKey();
                reference.updateChildren(map);

                DatabaseReference dbRef = reference.child(key);

                Map<String, Object> objectMap = new HashMap<String, Object>();

                objectMap.put("str_name", str_name);
                objectMap.put("text", et_msg.getText().toString());

                dbRef.updateChildren(objectMap);
                et_msg.setText("");
            }
        });



        /*

         inputName = (EditText) findViewById(R.id.name);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://chat-a4316.appspot.com/");

        //레퍼런스부분
        StorageReference pathReference = storageReference.child("4.jpg");

        //Url
        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
            @Override
            public void onSuccess(Uri uri) {
                Toast.makeText(getApplicationContext(), "다운로드 성공 : "+ uri, Toast.LENGTH_SHORT).show();
                inputName.setText(uri.toString());

            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(getApplicationContext(), "다운로드 실패", Toast.LENGTH_SHORT).show();
            }
        });

//        로컬 영역에 저장
        try {
            final File localFile = File.createTempFile("images", "jpg" );
            pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
                    inputName.setText(localFile.getPath());

                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    Toast.makeText(getApplicationContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
                }
            });
        } catch (IOException e) { Toast.makeText(getApplicationContext(), "예외가 발생했다 씨!!!!", Toast.LENGTH_SHORT).show();
            e.printStackTrace();
        }
         */

        /*
        addChildEventListener는 Child에서 일어나는 변화를 감지
        - onChildAdded()   : 리스트의 아이템을 검색하거나 추가가 있을 때 수신
        - onChildChanged() : 리스트의 아이템의 변화가 있을때 수신
        - onChildRemoved() : 리스트의 아이템이 삭제되었을때 수신
        - onChildMoved()   : 리스트의 순서가 변경되었을때 수신
         */

        reference.addChildEventListener(new ChildEventListener() {
            @Override public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                chatListener(dataSnapshot);

            }

            @Override public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                chatListener(dataSnapshot);
            }

            @Override public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    private void chatListener(DataSnapshot dataSnapshot) {
        // dataSnapshot 밸류값 가져옴
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            chat_user = (String) ((DataSnapshot) i.next()).getValue();
            str_msg = (String) ((DataSnapshot) i.next()).getValue();


            // 유저이름, 메시지를 가져와서 array에 추가
            arrayAdapter.add(chat_user + " : " + str_msg);
        }

        // 변경된값으로 리스트뷰 갱신
        arrayAdapter.notifyDataSetChanged();
    }

}
