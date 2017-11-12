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


public class JoinActivity extends AppCompatActivity {
    Context mContext;

    private EditText et_join_id;
    private EditText et_join_pw;
//    private EditText et_join_pw_ok;
    private EditText et_join_name;
    private EditText et_join_email;
    private CheckBox cb_join_clause;
    private CheckBox cb_join_personal;

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
        cb_join_clause = (CheckBox)findViewById(R.id.cb_join_clause);
        cb_join_personal = (CheckBox)findViewById(R.id.cb_join_personal);


    }
    //아이디 중복검사
    private void duplicateInspection(){
        //입력이 안 됫을 시
        if(et_join_id.getText().toString().length() == 0){
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            d.setMessage("아이디를 입력 해 주세요");
            d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            /*
            //처음 회원 가입 시
        }else if(personDTO.size() == 0){
            AlertDialog.Builder d = new AlertDialog.Builder(this);
            d.setMessage("사용 가능 한 아이디 입니다.");
            d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            d.show();
            //기존 회원 아이디와 비교
        }else {
            for (int i = 0; i < personDTO.size(); i++) {
                //중복 되었을 경우
                if (et_join_id.getText().toString().equalsIgnoreCase(personDTO.get(i).getId())) {
                    AlertDialog.Builder d = new AlertDialog.Builder(this);
                    d.setMessage("이미 사용중인 아이디 입니다.");
                    d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    d.show();
                }else{
                    AlertDialog.Builder d = new AlertDialog.Builder(this);
                    d.setMessage("사용 가능 한 아이디 입니다.");
                    d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
                        }
                    });
                    d.show();
                }
            }
             */
        }
    }
    //등록
    private boolean register(){

        //값이 하나라도 비어있으면
        boolean flag = true;
/*
        //중복 되었을 경우
        for (int i = 0; i < personDTO.size(); i++) {
            if (et_join_id.getText().toString().equalsIgnoreCase(personDTO.get(i).getId())) {
                flag = false;
            }
        }
        //칸 비어있는지 검사
        if(et_join_id.getText().toString().length() == 0 || et_join_pw.getText().length() == 0 ||
                et_join_name.getText().toString().length() == 0 || et_join_email.getText().toString().length() == 0){
            flag = false;
        }

        //이용약관 / 개인정보 취급방침 checkbox 검사
        if(!cb_join_clause.isChecked() || !cb_join_personal.isChecked()){
            flag = false;
        }

        //모든 조건을 만족하여 flag가 true일 때만 등록 실행

        if(flag){
            PersonDTO dto = new PersonDTO(
                    et_join_id.getText().toString(),
                    et_join_pw.getText().toString(),
                    et_join_name.getText().toString(),
                    et_join_email.getText().toString()
            );
            flag = personDTO.add(dto);
        }
        */


        return flag;
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
                boolean reg_ok = register();
                if(reg_ok) {
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
                }else{
                    AlertDialog.Builder d = new AlertDialog.Builder(this);
                    d.setMessage("등록 실패!! 양식을 다시 확인해 주세요.");
                    d.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.cancel();
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
