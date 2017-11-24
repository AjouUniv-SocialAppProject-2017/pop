package com.example.samsung.chat;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class ChatActivity extends AppCompatActivity {

    private EditText et_id;
    private EditText et_password;
    private EditText et_name;
    private EditText et_phonenum;

    private Button btn_send;
    private ArrayAdapter<String> arrayAdapter;

    private ArrayList<String> arr_user_id_list = new ArrayList<>();




    private DatabaseReference UserRef;
    private DatabaseReference MyRef=FirebaseDatabase.getInstance().getReference();
    private String key;
    private DataSnapshot dataSnapshot;
    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        et_id = (EditText) findViewById(R.id.et_id);
        et_password = (EditText) findViewById(R.id.et_password);
        et_name = (EditText) findViewById(R.id.et_name);
        et_phonenum = (EditText) findViewById(R.id.et_phonenum);
        btn_send = (Button) findViewById(R.id.btn_send);
        UserRef = FirebaseDatabase.getInstance().getReference().child("users");
        arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, arr_user_id_list);

        listView = (ListView) findViewById(R.id.list);

        listView.setAdapter(arrayAdapter);


        setTitle("테스팅페이지");

        btn_send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


//
               writeNewUser(et_id.getText().toString(),et_password.getText().toString(),et_name.getText().toString(),"3","4",et_phonenum.getText().toString());


                et_id.setText("");
                et_password.setText("");
                et_name.setText("");
                et_phonenum.setText("");

            }
        });



        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                Set<String> setid = new HashSet<String>();

                Iterator i = dataSnapshot.getChildren().iterator();

                while (i.hasNext()) {
                    setid.add(((DataSnapshot) i.next()).getValue(User.class).getId());

//                    setpassword.add(((DataSnapshot) i.next()).getValue(User.class).getId());
                }

                arr_user_id_list.clear();
                arr_user_id_list.addAll(setid);

                arrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        UserRef.addValueEventListener(UserListener);

    }








    private void writeNewUser(String id, String password, String user_name, String email, String major, String phone_num) {


        String key=MyRef.child("users").push().getKey();
        User user=new User(id,password,user_name,email,major,phone_num);
        Map<String, Object> userValues = user.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/users/" + key, userValues);


        MyRef.updateChildren(childUpdates);

//        arrayAdapter.notifyDataSetChanged();
    }
}




