package com.example.kyu.sap;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.text.Html;
import android.text.method.LinkMovementMethod;
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

/**
 * Created by Kyu on 2017-11-07.
 */

public class DetailActivity extends AppCompatActivity {
    public static String chatroom;
    public static ArrayList<Data> item_list = new ArrayList<>();
    private ImageView btn_comment_send;
    private EditText et_comment;
    private ListView comment_list;

    private ArrayAdapter<String> arrayAdapter2;

    private String str_name="황선욱";
    private String str_msg;
    private String chat_user;


   private DatabaseReference commentRef= FirebaseDatabase.getInstance()
            .getReference().child("comment");



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = getIntent();
        commentRef=FirebaseDatabase.getInstance()
                .getReference().child("comment").child(intent.getExtras().getString("pj_name"));
        ImageView project_img = (ImageView)findViewById(R.id.project_img);
        project_img.setBackground(ContextCompat.getDrawable(getApplicationContext(),intent.getExtras().getInt("project_img")));

        ImageView like_btn = (ImageView)findViewById(R.id.like_btn);
        boolean like = intent.getExtras().getBoolean("like_btn");
        if(like){
            like_btn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heart_on));
        }
        else{
            like_btn.setBackground(ContextCompat.getDrawable(getApplicationContext(),R.drawable.heart_off));
        }
        TextView pj_name = (TextView)findViewById(R.id.pj_name);
        pj_name.setText(intent.getExtras().getString("pj_name"));
        chatroom=intent.getExtras().getString("pj_name");


        TextView team_name = (TextView)findViewById(R.id.team);
        team_name.setText("팀 - " + intent.getExtras().getString("pj_name"));


        TextView uni = (TextView)findViewById(R.id.uni_txt);
        uni.setText(intent.getExtras().getString("uni_txt"));
        TextView major = (TextView)findViewById(R.id.major);
        major.setText(intent.getExtras().getString("major"));
        TextView presentation = (TextView)findViewById(R.id.presentation);
        presentation.setText(intent.getExtras().getString("presentation"));
        TextView video = (TextView)findViewById(R.id.video);
        video.setText(intent.getExtras().getString("video"));
        TextView member1 = (TextView)findViewById(R.id.member1);
        String member="";
        for(int i=0;i<intent.getExtras().getStringArrayList("member1").size();i++){
            member+=intent.getExtras().getStringArrayList("member1").get(i);
            member+=" ";
        }
        member1.setText(member);
        TextView tech = (TextView)findViewById(R.id.used_tech_txt1);
        String techs="";
        for(int i=0;i<intent.getExtras().getStringArrayList("used_tech_txt1").size();i++){
            techs+=intent.getExtras().getStringArrayList("used_tech_txt1").get(i);
            techs+=" ";
        }
        tech.setText(techs);


        presentation.setText(Html.fromHtml("<a href=\""+ presentation.getText()+"\">발표 자료 보기</a>"));
        presentation.setMovementMethod(LinkMovementMethod.getInstance());




        video.setText(Html.fromHtml("<a href=\""+video.getText()+"\">시연 영상 보기</a>"));
        video.setMovementMethod(LinkMovementMethod.getInstance());

        //댓글부분
        comment_list = (ListView) findViewById(R.id.comment_list);
        btn_comment_send = (ImageView) findViewById(R.id.btn_comment_send);
        et_comment = (EditText) findViewById(R.id.et_comment);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        comment_list.setAdapter(arrayAdapter2);

        // 리스트뷰가 갱신될때 하단으로 자동 스크롤
        comment_list.setTranscriptMode(ListView.TRANSCRIPT_MODE_ALWAYS_SCROLL);

//        str_name = "Guest " + new Random().nextInt(1000);

        btn_comment_send.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                // map을 사용해 name과 메시지를 가져오고, key에 값 요청
                Map<String, Object> map = new HashMap<String, Object>();

                // key로 데이터베이스 오픈
                String key = commentRef.push().getKey();
                commentRef.updateChildren(map);

                DatabaseReference dbRef = commentRef.child(key);

                Map<String, Object> objectMap = new HashMap<String, Object>();

                objectMap.put("str_name", str_name);
                objectMap.put("text", et_comment.getText().toString());

                dbRef.updateChildren(objectMap);
                et_comment.setText("");
            }
        });


        /*
        addChildEventListener는 Child에서 일어나는 변화를 감지
        - onChildAdded()   : 리스트의 아이템을 검색하거나 추가가 있을 때 수신
        - onChildChanged() : 리스트의 아이템의 변화가 있을때 수신
        - onChildRemoved() : 리스트의 아이템이 삭제되었을때 수신
        - onChildMoved()   : 리스트의 순서가 변경되었을때 수신
         */

        commentRef.addChildEventListener(new ChildEventListener() {
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
    public void chatOnClick(View v){
        switch(v.getId()){

            case R.id.chatButton:
                Intent intent2 = new Intent(this, ChatActivity.class);
                startActivity(intent2);
                break;



        }
    }

    private void chatListener(DataSnapshot dataSnapshot) {
        // dataSnapshot 밸류값 가져옴
        Iterator i = dataSnapshot.getChildren().iterator();

        while (i.hasNext()) {
            chat_user = (String) ((DataSnapshot) i.next()).getValue();
            str_msg = (String) ((DataSnapshot) i.next()).getValue();

            // 유저이름, 메시지를 가져와서 array에 추가
            arrayAdapter2.add(chat_user + "님의 댓글"+" : " + str_msg);
        }

        // 변경된값으로 리스트뷰 갱신
        arrayAdapter2.notifyDataSetChanged();
    }



}