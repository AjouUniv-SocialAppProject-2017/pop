package com.example.kyu.sap;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 * Created by Kyu on 2017-11-08.
 */

public class LoginActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);

        EditText content = new EditText(this);
        content.setGravity(Gravity.RIGHT);

        final EditText edit_id = (EditText) findViewById(R.id.loginid);
        final EditText edit_pw = (EditText) findViewById(R.id.loginpw);

        edit_id.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
        edit_pw.getBackground().setColorFilter(getResources().getColor(R.color.colorPrimaryDark), PorterDuff.Mode.SRC_ATOP);
    }

    public void mOnClick(View v) {

        switch(v.getId()){
            case R.id.login_button:
                startActivity(new Intent(LoginActivity.this, MainActivity.class));
                break;
            case R.id.join_button:
                startActivity(new Intent(LoginActivity.this, JoinActivity.class));
                break;
        }
    }
}










