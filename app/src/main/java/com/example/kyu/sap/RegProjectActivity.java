package com.example.kyu.sap;

import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import java.io.IOException;
import java.util.List;

/**
 * Created by Kyu on 2017-05-14.
 */

public class RegProjectActivity extends AppCompatActivity {

    private static final int PICK_FROM_CAMERA = 1;
    private static final int PICK_FROM_GALLERY = 2;
    private ImageView reg_roomImage;

    private EditText reg_title;
    private EditText reg_address;
    private EditText reg_category;
    private EditText reg_dealType;
    private EditText reg_price;
    private EditText reg_size;
    private EditText reg_floorNum;
    private EditText reg_roomNum;
    private EditText reg_inputDate;
    private EditText reg_summary;
    private EditText reg_phoneCall;


    Bitmap photo = null;
    //    Drawable reg_roomImage = null;
    Drawable temp = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.regroom_detail);

        reg_roomImage = (ImageView) findViewById(R.id.reg_roomImage);
        //방 이미지 등록
        reg_roomImage.setImageResource(R.drawable.p_icon);

//        intent = getIntent();

    }

    public void mOnClick(View v){
        switch(v.getId()){
            //+버튼 누르면
            case R.id.reg_roomImage:

                Intent intent = new Intent();
                // Gallery 호출
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                // 잘라내기 셋팅
                intent.putExtra("crop", "true");
                intent.putExtra("aspectX", 0);
                intent.putExtra("aspectY", 0);
                intent.putExtra("outputX", 200);
                intent.putExtra("outputY", 150);
                try {
                    intent.putExtra("return-data", true);
                    startActivityForResult(Intent.createChooser(intent,
                            "Complete action using"), PICK_FROM_GALLERY);
                } catch (ActivityNotFoundException e) {
                    // Do nothing for now
                }
                break;
            //완료버튼
            case R.id.btn_register:

                break;

            default:
                break;
        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (requestCode == PICK_FROM_CAMERA) {
            Bundle extras = data.getExtras();
            if (extras != null) {
                photo = extras.getParcelable("data");
                //reg_roomImage.setImageBitmap(photo);
            }
        }
        if (requestCode == PICK_FROM_GALLERY) {
            Bundle extras2 = data.getExtras();
            if (extras2 != null) {
                photo = extras2.getParcelable("data");
                //reg_roomImage.setImageBitmap(photo);
            }
        }

        reg_roomImage.setImageBitmap(photo);

        //Drawable 객체를 받아와서
        //temp = getDrawableFromBitmap(photo);

        if(photo == null){
            Toast.makeText(getApplicationContext(), "photo 없음", Toast.LENGTH_SHORT).show();
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












