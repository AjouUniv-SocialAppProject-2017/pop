package com.example.kyu.sap;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CalendarView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static android.R.attr.data;
import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.example.kyu.sap.ProfileFragment.getRoundedCornerBitmap;
//import static com.example.kyu.sap.TimeLineFragment.Adapter;
import static com.example.kyu.sap.TimeLineFragment.DataRef;
import static com.example.kyu.sap.TimeLineFragment.item_list;

/**
 * Created by Kyu on 2017-05-14.
 */

public class RegProjectActivity extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    public static DataSnapshot ds2;

    private ImageView reg_projectImage;

    private EditText reg_pjname;
    private EditText reg_university;
    private EditText reg_major;
    private EditText reg_summary;
    private EditText reg_like;
    private EditText reg_img;
    private Button reg_presentation;
    private Button reg_video;
    private EditText reg_number_of_like;
    private EditText reg_date;
    private EditText reg_member;
    private EditText reg_hash_tag;
    private CalendarView calView;
    private Uri filePath;
    Bitmap photo = null;
    //    Drawable reg_roomImage = null;
    Drawable temp = null;
    Calendar calendar = Calendar.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regroom_detail);

        reg_projectImage = (ImageView) findViewById(R.id.reg_projectImage);
        //방 이미지 등록
        reg_projectImage.setImageResource(R.drawable.p_icon);
        reg_member = (EditText) findViewById(R.id.reg_member);
        reg_hash_tag = (EditText) findViewById(R.id.reg_hash_tag);
        reg_date = (EditText) findViewById(R.id.reg_date);

        //회원가입을 할때마다 사용자들의 아이디를 setid에 누적
        ValueEventListener DataListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                ds2 = dataSnapshot;
                Log.d("bul", "bul");
                Log.d("bul", "bul");
                Log.d("bul", "bul");
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
        DataRef.addValueEventListener(DataListener);
    }

    //등록하기
    private void registerProject() {

        String key = DataRef.push().getKey();

        reg_pjname = (EditText)findViewById(R.id.reg_pjname);
        reg_university = (EditText)findViewById(R.id.reg_university);
        reg_major = (EditText)findViewById(R.id.reg_major);
        reg_summary = (EditText)findViewById(R.id.reg_summary);
        //사진 생략
        reg_presentation = (Button)findViewById(R.id.reg_presentation);
        reg_video = (Button)findViewById(R.id.reg_video);
        //좋아요 생략
        //Number Of Like 생략

        Data data = new Data(
                reg_pjname.getText().toString(),
                reg_university.getText().toString(),
                reg_major.getText().toString(),
                reg_summary.getText().toString(),
                R.drawable.cat1,
                R.drawable.face,
                reg_presentation.getText().toString(),
                reg_video.getText().toString(),
                true,
                1);

        //Adapter.addProject(data);

        Map<String, Object> dataValues = data.toMap();

        Map<String, Object> childUpdates = new HashMap<>();

        childUpdates.put(key, dataValues);
        DataRef.updateChildren(childUpdates);

        //Data imsi_data = new Data("Place Of Passion","아주대학교","미디어학과","졸업작품 정보 제공 서비스",R.drawable.cat1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/user/ajouuniversity",true,217);
        //Adapter.addProject(imsi_data);
    }

    //가져오기
    private void rkwudhrl(){

        //가져오는 부분
        Iterator i = ds2.getChildren().iterator();

        Data get_data = new Data();
        //String imsiPj_name = "";

        String imsiPjname = "";
        String imsiUniversity = "";
        String imsiMajor = "";
        String imsiSummary = "";
        boolean imsiLike = true;
        int imsiImg = 1;
        int imsiFaceimg = 1;
        String imsiPresentation = "";
        String imsiVideo = "";
        int imsiNumberOfLike = 1;


        while (i.hasNext()) {

            get_data = ((DataSnapshot) i.next()).getValue(Data.class);

            imsiPjname = get_data.getPj_name();
            imsiUniversity = get_data.getUniversity();
            imsiMajor = get_data.getMajor();
            imsiSummary = get_data.getSummary();
            imsiLike = get_data.isLike();
            imsiImg = get_data.getImg();
            imsiFaceimg = get_data.getFace_img();
            imsiPresentation = get_data.getPresentation();
            imsiVideo = get_data.getVideo();
            imsiNumberOfLike = get_data.getNumber_of_like();
        }


        item_list.add(new Data(imsiPjname, imsiUniversity, imsiMajor, imsiSummary, R.drawable.team2_, R.drawable.face ,"https://drive.google.com/open?id=1uazGZyGqD1n0GChyeeGBQlCdd6xYQFXQ", "https://drive.google.com/open?id=1uCh-iIt-G_thtgLabtQzQI6oidHczB6Q", imsiLike, 1));
        item_list.get(item_list.size()-1).addMember(reg_member.getText().toString());
        item_list.get(item_list.size()-1).addTech(reg_hash_tag.getText().toString());

        Toast.makeText(getApplicationContext(), "등록 완료 되었습니다.", Toast.LENGTH_SHORT).show();

    }

    public void mOnClick(View v){
        switch(v.getId()){
            //+버튼 누르면
            case R.id.reg_projectImage:
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
                break;
            //완료버튼
            case R.id.btn_register:
                registerProject();
                rkwudhrl();
                Intent reg_ok = new Intent(RegProjectActivity.this, MainActivity.class);
                reg_ok.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(reg_ok);
                break;
            //case R.id.btn_ghkrdls:
           //     break;
            case R.id.reg_presentation:
                Intent intent2 = new Intent();
                intent2.setType("application/pdf");
                intent2.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(Intent.createChooser(intent2, "pdf파일을 선택하세요."), 0);
                break;
            case R.id.upload_presentation:
                uploadFile();
                break;
            case R.id.reg_video:

                Intent intent3 = new Intent();
                intent3.setType("video/mp4");
                intent3.setAction(Intent.ACTION_GET_CONTENT);
                //
                startActivityForResult(Intent.createChooser(intent3, "mp4 파일을 선택하세요."), 0);
                break;

            case R.id.upload_video:
                uploadFile_video();
                break;
            default:
                break;
        }
    }

    private void uploadFile() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".pdf";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("pdf/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    private void uploadFile_video() {
        //업로드할 파일이 있으면 수행
        if (filePath != null) {
            //업로드 진행 Dialog 보이기
            final ProgressDialog progressDialog = new ProgressDialog(this);
            progressDialog.setTitle("업로드중...");
            progressDialog.show();

            //storage
            FirebaseStorage storage = FirebaseStorage.getInstance();

            //Unique한 파일명을 만들자.
            SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMHH_mmss");
            Date now = new Date();
            String filename = formatter.format(now) + ".mp4";
            //storage 주소와 폴더 파일명을 지정해 준다.
            StorageReference storageRef = FirebaseStorage.getInstance().getReference().child("video/" + filename);
            //올라가거라...
            storageRef.putFile(filePath)
                    //성공시
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            progressDialog.dismiss(); //업로드 진행 Dialog 상자 닫기
                            Toast.makeText(getApplicationContext(), "업로드 완료!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //실패시
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            progressDialog.dismiss();
                            Toast.makeText(getApplicationContext(), "업로드 실패!", Toast.LENGTH_SHORT).show();
                        }
                    })
                    //진행중
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(UploadTask.TaskSnapshot taskSnapshot) {
                            @SuppressWarnings("VisibleForTests") //이걸 넣어 줘야 아랫줄에 에러가 사라진다. 넌 누구냐?
                                    double progress = (100 * taskSnapshot.getBytesTransferred()) / taskSnapshot.getTotalByteCount();
                            //dialog에 진행률을 퍼센트로 출력해 준다
                            progressDialog.setMessage("Uploaded " + ((int) progress) + "% ...");
                        }
                    });
        } else {
            Toast.makeText(getApplicationContext(), "파일을 먼저 선택하세요.", Toast.LENGTH_SHORT).show();
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {


        if (requestCode == 0 && resultCode == RESULT_OK) {
            filePath = data.getData();
            //Log.d(TAG, "uri:" + String.valueOf(filePath));
            try {
                //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                //ivPreview.setImageBitmap(bitmap);
                Toast.makeText(this, "업로드 버튼을 눌러주세요", Toast.LENGTH_SHORT).show();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


        if(requestCode == 100)
        {
            if(requestCode == 0 && resultCode == RESULT_OK){
                filePath = data.getData();
                //Log.d(TAG, "uri:" + String.valueOf(filePath));
                try {
                    //Uri 파일을 Bitmap으로 만들어서 ImageView에 집어 넣는다.
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(getContentResolver(), filePath);
                    //ivPreview.setImageBitmap(bitmap);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    ImageView image = (ImageView) findViewById(R.id.reg_projectImage);
                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);
                    Toast.makeText(getApplicationContext(), "사진이 등록되었습니다.", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (IOException e) {
                    // TODO Auto-generated catch block
                    e.printStackTrace();
                } catch (Exception e){
                    e.printStackTrace();
                }
            }
        }
    }

    //bitmap 을 Drawable 객체로 반환
    /*
    public Drawable getDrawableFromBitmap(Bitmap bitmap){
        Drawable d = new BitmapDrawable(bitmap);
        return d;
    }
    */
}












