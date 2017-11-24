package com.example.kyu.sap;

import android.app.Activity;
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
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import static android.support.v7.widget.AppCompatDrawableManager.get;
import static com.example.kyu.sap.ProfileFragment.getRoundedCornerBitmap;

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
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);
                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent, 100);
                break;
            //완료버튼
            case R.id.btn_register:
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
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getApplicationContext().getContentResolver(), data.getData());
                    ImageView image = (ImageView) findViewById(R.id.reg_roomImage);
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












