package com.example.kyu.sap;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.util.Iterator;

import static com.example.kyu.sap.JoinActivity.UserRef;
import static com.example.kyu.sap.JoinActivity.ds;
import static com.example.kyu.sap.R.id.et_join_id;
import static com.example.kyu.sap.R.layout.login;

/**
 * Created by Kyu on 2017-11-08.
 */

public class LoginActivity extends AppCompatActivity {

    private EditText loginId;
    private EditText loginPw;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(login);

        EditText content = new EditText(this);
        content.setGravity(Gravity.RIGHT);

        loginId = (EditText)findViewById(R.id.loginid);
        loginPw = (EditText)findViewById(R.id.loginpw);

        final EditText edit_id = (EditText) findViewById(R.id.loginid);
        final EditText edit_pw = (EditText) findViewById(R.id.loginpw);

        edit_id.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        edit_pw.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);

    }

    public void loginInspection(){

        Iterator i = ds.getChildren().iterator();

        int su = 0;
        //int inspect = 0;
        User user = new User();
        while (i.hasNext()) {

            user = ((DataSnapshot) i.next()).getValue(User.class);

            String imsi_id = user.getId();
            String imsi_pw = user.getPassword();

            //아이디가 있으면
            if(loginId.getText().toString().equals(imsi_id)) {
                su = 1;
                if(loginPw.getText().toString().equals(imsi_pw)){
                    su = 2;
                }
            }
        }

//        if(su == 1){
//            Toast.makeText(this, user.getId() + " " + user.getPassword() , Toast.LENGTH_SHORT).show();

        if(su == 0){
            AlertDialog.Builder d1 = new AlertDialog.Builder(this);
            d1.setMessage("없는 아이디 입니다");
            d1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            d1.show();
        }else if(su == 1){
            AlertDialog.Builder d1 = new AlertDialog.Builder(this);
            d1.setMessage("비밀번호를 확인해주세요");
            d1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            d1.show();
        }else{
            AlertDialog.Builder d1 = new AlertDialog.Builder(this);
            d1.setMessage("로그인 성공!!");
            d1.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                public void onClick(DialogInterface dialog, int which) {
                    dialog.cancel();
                }
            });
            d1.show();
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        }

    }

    public void mOnClick(View v) {

        switch(v.getId()){
            case R.id.login_button:
                //회원가입 하고 로그인 이슈 => 막아놓음
                //loginInspection();
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.join_button:
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                break;
        }
    }
}










