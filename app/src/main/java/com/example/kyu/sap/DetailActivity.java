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

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.example.kyu.sap.LoginActivity.current_user_name;
import static com.example.kyu.sap.R.id.btn_comment_send;
import static com.example.kyu.sap.R.id.et_comment;

/**
 * Created by Kyu on 2017-11-07.
 */

public class DetailActivity extends AppCompatActivity {

    private DatabaseReference commentRef= FirebaseDatabase.getInstance()
            .getReference().child("comment");

    public static String chatroom;

    private String str_msg;
    private String chat_user;

    private ArrayAdapter<String> arrayAdapter2;

    private ImageView btn_comment_send;
    private EditText et_comment;
    private ListView comment_list;

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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.detail);

        Intent intent = getIntent();
        chatroom=intent.getExtras().getString("pj_name");
        commentRef=FirebaseDatabase.getInstance().getReference().child("comment").child(intent.getExtras().getString("pj_name"));

        //댓글부분
        comment_list = (ListView) findViewById(R.id.comment_list);
        btn_comment_send = (ImageView) findViewById(R.id.btn_comment_send);
        et_comment = (EditText) findViewById(R.id.et_comment);

        arrayAdapter2 = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1);
        comment_list.setAdapter(arrayAdapter2);

        //프로젝트 이미지 세팅
        ImageView project_img = (ImageView)findViewById(R.id.project_img);
        project_img.setBackground(ContextCompat.getDrawable(getApplicationContext(),intent.getExtras().getInt("project_img")));

        //face 이미지 세팅
        ImageView face_img = (ImageView)findViewById(R.id.face);
        face_img.setBackground(ContextCompat.getDrawable(getApplicationContext(),intent.getExtras().getInt("face_img")));



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

        btn_comment_send.setOnClickListener(new View.OnClickListener() {
            @Override public void onClick(View view) {

                // map을 사용해 name과 메시지를 가져오고, key에 값 요청
                Map<String, Object> map = new HashMap<String, Object>();

                // key로 데이터베이스 오픈
                String key = commentRef.push().getKey();
                commentRef.updateChildren(map);

                DatabaseReference dbRef = commentRef.child(key);

                Map<String, Object> objectMap = new HashMap<String, Object>();

                objectMap.put("str_name", current_user_name);
                objectMap.put("text", et_comment.getText().toString());

                dbRef.updateChildren(objectMap);
                et_comment.setText("");
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

}