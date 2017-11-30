package com.example.samsung.chat;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChatActivity extends AppCompatActivity {


    private StorageReference mStorageRef;

    private EditText inputName;

    private DataSnapshot dataSnapshot;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);
        inputName = (EditText) findViewById(R.id.name);

        FirebaseStorage storage = FirebaseStorage.getInstance();
        StorageReference storageReference = storage.getReferenceFromUrl("gs://chat-a4316.appspot.com/");

        //다운로드할 파일을 가르키는 참조 만들기
        StorageReference pathReference = storageReference.child("4.jpg");

        //Url을 다운받기
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

        //휴대폰 로컬 영역에 저장하기
//        try {
//            final File localFile = File.createTempFile("images", "jpg" );
//            pathReference.getFile(localFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
//                @Override
//                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
//                    Toast.makeText(getApplicationContext(), "파일 저장 성공", Toast.LENGTH_SHORT).show();
//                    inputName.setText(localFile.getPath());
//
//                }
//            }).addOnFailureListener(new OnFailureListener() {
//                @Override
//                public void onFailure(@NonNull Exception e) {
//                    Toast.makeText(getApplicationContext(), "파일 저장 실패", Toast.LENGTH_SHORT).show();
//                }
//            });
//        } catch (IOException e) { Toast.makeText(getApplicationContext(), "예외가 발생했다 씨!!!!", Toast.LENGTH_SHORT).show();
//            e.printStackTrace();
//        }


    }



}




