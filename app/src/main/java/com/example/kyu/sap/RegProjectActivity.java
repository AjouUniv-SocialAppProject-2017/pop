package com.example.kyu.sap;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static com.example.kyu.sap.TimeLineFragment.DataRef;
import static com.example.kyu.sap.TimeLineFragment.item_list;

//import static com.example.kyu.sap.TimeLineFragment.Adapter;

/**
 * Created by Kyu on 2017-05-14.
 */

public class RegProjectActivity extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    public static DataSnapshot ds2;
    public static int save_reg_id_num;

    private ImageView reg_projectImage;

    private EditText reg_pjname;
    private EditText reg_university;
    private EditText reg_major;
    private EditText reg_summary;
    private EditText reg_like;
    private EditText reg_img;
    private EditText reg_presentation;
    private EditText reg_video;
    private EditText reg_number_of_like;
    private EditText reg_date;
    private EditText reg_member;
    private EditText reg_hash_tag;

    Bitmap photo = null;
    //    Drawable reg_roomImage = null;
    Drawable temp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regroom_detail);

        reg_projectImage = (ImageView) findViewById(R.id.reg_projectImage);
        //방 이미지 등록
        reg_projectImage.setImageResource(R.drawable.p_icon);

        reg_member = (EditText) findViewById(R.id.reg_member);
        reg_hash_tag = (EditText) findViewById(R.id.reg_hash_tag);
        //리스너

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
        reg_presentation = (EditText)findViewById(R.id.reg_presentation);
        reg_video = (EditText)findViewById(R.id.reg_video);
        reg_date = (EditText)findViewById(R.id.reg_date);
        //좋아요 생략
        //Number Of Like 생략

        Data data = new Data(reg_pjname.getText().toString(),
                reg_university.getText().toString(),
                reg_major.getText().toString(),
                reg_summary.getText().toString(),
                R.drawable.cat1,
                reg_presentation.getText().toString(),
                reg_video.getText().toString(),
                true,
                217,reg_date.getText().toString());

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
        String imsiPresentation = "";
        String imsiVideo = "";
        int imsiNumberOfLike = 1;
        String imsiGet_date = "";


        while (i.hasNext()) {

            get_data = ((DataSnapshot) i.next()).getValue(Data.class);

            imsiPjname = get_data.getPj_name();
            imsiUniversity = get_data.getUniversity();
            imsiMajor = get_data.getMajor();
            imsiSummary = get_data.getSummary();
            imsiLike = get_data.isLike();
            imsiImg = get_data.getImg();
            imsiPresentation = get_data.getPresentation();
            imsiVideo = get_data.getVideo();
            imsiNumberOfLike = get_data.getNumber_of_like();
            imsiGet_date = get_data.getReg_date();
        }


        //이부분 실시간 업데이트 해결 해야함//



        item_list.add(new Data(imsiPjname, imsiUniversity, imsiMajor, imsiSummary, imsiImg, imsiPresentation, imsiVideo, imsiLike, imsiNumberOfLike,imsiGet_date));
        item_list.get(3).addMember(reg_member.getText().toString());
        //item_list.get(3).addMember("10번째");
        //item_list.get(3).addMember("10번째");
        item_list.get(3).addTech(reg_hash_tag.getText().toString());
        //item_list.get(3).addTech("#뷰페이저");
        //item_list.get(3).addTech("#안드로이드");

        Toast.makeText(getApplicationContext(), imsiMajor +", " + item_list.size() + "등록 완료 되었습니다.", Toast.LENGTH_SHORT).show();

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

                break;
            case R.id.btn_ghkrdls:
                rkwudhrl();
                Intent reg_ok = new Intent(RegProjectActivity.this, MainActivity.class);
                reg_ok.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(reg_ok);
                break;
            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == 100)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap    = MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
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











