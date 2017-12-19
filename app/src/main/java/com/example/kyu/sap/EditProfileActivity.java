package com.example.kyu.sap;

import android.content.Context;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

import static com.example.kyu.sap.LoginActivity.current_user_name;

/**
 * Created by SAMSUNG on 2017-12-19.
 */

public class EditProfileActivity extends AppCompatActivity {


    Context mContext;

    private EditText et_join_id2;
    private EditText et_join_pw2;
    //    private EditText et_join_pw_ok;
    private EditText et_join_name2;
    private EditText et_join_email2;
    private CheckBox cb_join_clause2;
    private CheckBox cb_join_personal2;

    private TextView tv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editprofile);
        mContext = EditProfileActivity.this;

        tv = (TextView)findViewById(R.id.set_user_id);

        tv.setText(current_user_name);

//        et_join_id2 = (EditText)findViewById(R.id.et_join_id2);
//        et_join_pw2 = (EditText)findViewById(R.id.et_join_pw2);
//        et_join_pw_ok = (EditText)findViewById(R.id.et_join_pw_ok);
//        et_join_name2 = (EditText)findViewById(R.id.et_join_name2);
//        et_join_email2 = (EditText)findViewById(R.id.et_join_email2);
//        cb_join_clause2 = (CheckBox)findViewById(R.id.cb_join_clause2);
//        cb_join_personal2 = (CheckBox)findViewById(R.id.cb_join_personal2);

    }
}