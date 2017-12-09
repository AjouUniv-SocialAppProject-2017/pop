package com.example.kyu.sap;

import android.support.v7.app.AppCompatActivity;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;



public class JoinActivity extends AppCompatActivity {

    public static DatabaseReference UserRef = FirebaseDatabase.getInstance().getReference().child("users");

    private boolean login_flag = true;
    Context mContext;

    private EditText et_join_id;
    private EditText et_join_pw;
//    private EditText et_join_pw_ok;
    private EditText et_join_name;
    private EditText et_join_email;
    private EditText et_join_phone_num;
    private EditText et_join_major;
    private CheckBox cb_join_clause;
    private CheckBox cb_join_personal;

    public static DataSnapshot ds;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.join);
        mContext = JoinActivity.this;

        et_join_id = (EditText)findViewById(R.id.et_join_id);
        et_join_pw = (EditText)findViewById(R.id.et_join_pw);
//        et_join_pw_ok = (EditText)findViewById(R.id.et_join_pw_ok);
        et_join_name = (EditText)findViewById(R.id.et_join_name);
        et_join_email = (EditText)findViewById(R.id.et_join_email);
        et_join_phone_num = (EditText)findViewById(R.id.et_join_phone_num);
        et_join_major = (EditText)findViewById(R.id.et_join_major);

        cb_join_clause = (CheckBox)findViewById(R.id.cb_join_clause);
        cb_join_personal = (CheckBox)findViewById(R.id.cb_join_personal);




        //회원가입을 할때마다 사용자들의 아이디를 setid에 누적
        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                ds = dataSnapshot;

                //리스트뷰 업데이트
                //arr_user_id_list.clear();
                //arr_user_id_list.addAll(setid);
                //arrayAdapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message
            }
        };
        UserRef.addValueEventListener(UserListener);

        //ProjectRef.child("users").addValueEventListener(UserListener);
    }

    //아이디 중복검사
    private void duplicateInspection(){
        //Set<String> setid = new HashSet<String>();

        Iterator i = ds.getChildren().iterator();
        int inspect = 0;
        while (i.hasNext()) {

            String imsi = ((DataSnapshot) i.next()).getValue(User.class).getId();

            if(et_join_id.getText().toString().equals(imsi)){
                inspect = 1;
                login_flag = false;
            }else if(et_join_id.getText().toString() == null){
                inspect = 2;
                login_flag = false;
            }else {
                inspect = 3;
                login_flag = true;
            }
        }

        switch(inspect){
            case 1:
                AlertDialog.Builder d1 = new AlertDialog.Builder(this);
                d1.setMessage("중복된 아이디 입니다");
                d1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                d1.show();
                break;
            case 2:
                AlertDialog.Builder d2 = new AlertDialog.Builder(this);
                d2.setMessage("아이디를 입력해 주세요");
                d2.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                d2.show();
                break;
            case 3:
                AlertDialog.Builder d3 = new AlertDialog.Builder(this);
                d3.setMessage("사용 가능한 아이디 입니다.");
                d3.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                d3.show();
                break;
        }
    }

    //등록 부분
    private void register(){

        //모든 조건을 만족하여 flag가 true일 때만 등록 실행
        String key=UserRef.push().getKey();

        User user=new User(et_join_id.getText().toString(),
                et_join_pw.getText().toString(),et_join_name.getText().toString(),
                et_join_email.getText().toString(),et_join_phone_num.getText().toString(),
                et_join_major.getText().toString());

        Map<String, Object> userValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();


        childUpdates.put(key, userValues);
//        childUpdates.put(key2, userValues);

        UserRef.updateChildren(childUpdates);


        //등록 후 알림 메시지
        AlertDialog.Builder d = new AlertDialog.Builder(this);
        d.setMessage("등록 성공!!");
        //확인버튼을 누르면 intent 실행
        d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
                Intent intent = new Intent(mContext, LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                finish();
            }
        });
        d.show();
    }

    public void blankinspection(){

        //값이 하나라도 비어있으면
        //칸 비어있는지 검사
        if(et_join_id.getText().toString().length() == 0 || et_join_pw.getText().length() == 0 ||
                et_join_name.getText().toString().length() == 0 || et_join_email.getText().toString().length() == 0){
            login_flag = false;
        }

        //이용약관 / 개인정보 취급방침 checkbox 검사
        if(!cb_join_clause.isChecked() || !cb_join_personal.isChecked()){
            login_flag = false;
        }

    }

    public void mOnClick(View v){
        switch (v.getId()){
            //이용약관 내용보기
            case R.id.btn_join_clause:
                Intent intent_useRule = new Intent(mContext, com.example.kyu.sap.UseRule.class);
                startActivity(intent_useRule);
                break;
            //개인정보 취급방침 내용보기
            case R.id.btn_join_personal:
                Intent intent_personRule = new Intent(mContext, com.example.kyu.sap.PersonRule.class);
                startActivity(intent_personRule);
                break;
            //등록 버튼
            case R.id.btn_join_ok:

                //칸 비어있는지 검사
                blankinspection();
                //DB에 등록
                if (login_flag) {
                    register();
                } else {
                    AlertDialog.Builder d = new AlertDialog.Builder(this);
                    d.setMessage("등록 실패!!");
                    //확인버튼을 누르면 intent 실행
                    d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                            Intent intent = new Intent(mContext, LoginActivity.class);
                            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                            startActivity(intent);
                            finish();
                        }
                    });
                    d.show();
                }
                break;
            //아이디 중복검사
            case R.id.btn_join_id_check:
                duplicateInspection();
                break;
            default:
                break;
        }
    }
}
