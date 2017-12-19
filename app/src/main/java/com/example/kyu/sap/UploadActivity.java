package com.example.kyu.sap;

import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.media.Image;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.storage.FileDownloadTask;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import static com.example.kyu.sap.R.id.imageView;

/**
 * Created by Kyu on 2017-12-17.
 */

public class UploadActivity extends AppCompatActivity {
    private static final String TAG = "MainActivity";

    private Button btChoose;
    private Button btUpload;
    private ImageView ivPreview;

    private Uri filePath;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.upload);

        btChoose = (Button) findViewById(R.id.bt_choose);
        btUpload = (Button) findViewById(R.id.bt_upload);
        ivPreview = (ImageView) findViewById(R.id.iv_preview);

        //버튼 클릭 이벤트
        btChoose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //이미지를 선택
                Intent intent = new Intent();
                intent.setType("application/pdf");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent, "pdf파일을 선택하세요."), 0);
            }
        });

        btUpload.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //업로드
                uploadFile();
            }
        });

    }

    //결과 처리
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        //request코드가 0이고 OK를 선택했고 data에 뭔가가 들어 있다면
        if (requestCode == 0 && resultCode == RESULT_OK) {
            filePath = data.getData();
            Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                ivPreview.setImageBitmap(bitmap);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //upload the file
    private void uploadFile() {

        String filename = "gs://popsocialappproject.appspot.com/pdf/20171219_3002.pdf";
        openPDF(filename.trim());

    }

    public void openPDF(String contentsPath){
        File file = new File(contentsPath);

        if(file.exists()){
            Uri path = Uri.fromFile(file);

            Intent intent = new Intent(Intent.ACTION_VIEW);
            intent.setDataAndType(path, "application/pdf");
            intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);

            try{
                startActivity(intent);
            }catch(ActivityNotFoundException e){
                Toast.makeText(this, "asdf", Toast.LENGTH_SHORT).show();
            }

        }else{
            Toast.makeText(this,"sdfg", Toast.LENGTH_SHORT).show();
        }
    }
}

        /*
        iv = (ImageView)findViewById(R.id.iv);
        int imageNum = 2;

        mStorageRef = FirebaseStorage.getInstance().getReference();

        String folderName = "images";

        String imageName = "imsi.pdf";

        //String imageName = String.format("face%d.jpg", imageNum);

        // Storage 이미지 다운로드 경로
        String storagePath = folderName + "/" + imageName;

        StorageReference imageRef = mStorageRef.child(storagePath);

        try {
            // Storage 에서 다운받아 저장시킬 임시파일
            //final File imageFile = File.createTempFile("images", "jpg");
            final File imageFile = File.createTempFile("imsi", "pdf");
            imageRef.getFile(imageFile).addOnSuccessListener(new OnSuccessListener<FileDownloadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(FileDownloadTask.TaskSnapshot taskSnapshot) {
                    // PDF Success Case

                    Uri path = Uri.fromFile(imageFile);
                    Intent pdfIntent = new Intent(Intent.ACTION_VIEW);
                    pdfIntent.setDataAndType(path, "application/pdf");
                    //pdfIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                    startActivity(pdfIntent);

                    //Bitmap bitmapImage = BitmapFactory.decodeFile(imageFile.getPath());
                    //iv.setImageBitmap(bitmapImage);
                    Toast.makeText(getApplicationContext(), "Success !!", Toast.LENGTH_LONG).show();
                }
            }).addOnFailureListener(new OnFailureListener() {
                @Override
                public void onFailure(@NonNull Exception e) {
                    // Fail Case
                    e.printStackTrace();
                    Toast.makeText(getApplicationContext(), "Fail !!", Toast.LENGTH_LONG).show();
                }
            });
        } catch (IOException e) {
            e.printStackTrace();
        }
*/

