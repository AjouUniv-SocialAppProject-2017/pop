package com.example.kyu.sap;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Rect;
import android.graphics.RectF;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by Kyu on 2017-11-08.
 */

public class ProfileFragment extends Fragment {

    final int REQ_CODE_SELECT_IMAGE=100;



    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.profile, null);

        View.OnClickListener detail = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                ArrayList<Data> item_list = new ArrayList<>();
                item_list.add(new Data("PoP","아주대학교","미디어학과","졸업작품 정보 제공 서비스",R.drawable.logo7,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/user/inhauniversity",true,217));
                item_list.get(0).addMember("김규서");
                item_list.get(0).addMember("황선욱");
                item_list.get(0).addMember("홍길동");
                item_list.get(0).addTech("#리스트뷰");
                item_list.get(0).addTech("#뷰페이저");
                item_list.get(0).addTech("#안드로이드");
                final Data data = item_list.get(0);
                intent.putExtra("project_img", data.getImg());
                intent.putExtra("like_btn", data.isLike());
                intent.putExtra("pj_name", data.getPj_name());
                intent.putExtra("uni_txt", data.getUniversity());
                intent.putExtra("major", data.getMajor());
                intent.putExtra("presentation", data.getPresentation());
                intent.putExtra("video", data.getVideo());
                intent.putExtra("member1", data.getMembers());
                intent.putExtra("used_tech_txt1", data.getTechs());
                startActivity(intent);

            }
        };


        ImageView imag = (ImageView) view.findViewById(R.id.profile_imageview);

        Bitmap bm1 = BitmapFactory.decodeResource(getResources(), R.drawable.profileimage);
        Bitmap bm2 = getRoundedCornerBitmap(bm1);
        imag.setImageBitmap(bm2);

        imag.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);


            }
        });

        //내작품정보
        TextView myp = (TextView) view.findViewById(R.id.myproject);
        myp.setOnClickListener(detail);

        TextView txtv = (TextView) view.findViewById(R.id.modify_profile);
        txtv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);

                intent.setType(android.provider.MediaStore.Images.Media.CONTENT_TYPE);

                intent.setData(android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);

                startActivityForResult(intent, REQ_CODE_SELECT_IMAGE);
            }
        });


        TextView logout = (TextView) view.findViewById(R.id.log_out);

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);

                ProfileFragment.this.startActivity(intent);

                //수정
                Toast.makeText(getActivity(), "로그아웃 되었습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }


    public static Bitmap getRoundedCornerBitmap(Bitmap bitmap) {
        Bitmap output = Bitmap.createBitmap(bitmap.getWidth(),
                bitmap.getHeight(), Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(output);
        final int color = 0xff424242;
        final Paint paint = new Paint();
        final Rect rect = new Rect(0, 0, bitmap.getWidth(), bitmap.getHeight());
        final RectF rectF = new RectF(rect);
        final float roundPx = 12;
        paint.setAntiAlias(true);
        canvas.drawARGB(0, 0, 0, 0);
        paint.setColor(color);
        canvas.drawRoundRect(rectF, roundPx, roundPx, paint);
        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.SRC_IN));
        canvas.drawBitmap(bitmap, rect, rect, paint);
        return output;
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == REQ_CODE_SELECT_IMAGE)
        {
            if(resultCode== Activity.RESULT_OK)
            {
                try {
                    //Uri에서 이미지 이름을 얻어온다.
                    //String name_Str = getImageNameToUri(data.getData());
                    //이미지 데이터를 비트맵으로 받아온다.
                    Bitmap image_bitmap 	= MediaStore.Images.Media.getBitmap(getActivity().getContentResolver(), data.getData());
                    ImageView image = (ImageView) getView().findViewById(R.id.profile_imageview);
                    //배치해놓은 ImageView에 set
                    image.setImageBitmap(image_bitmap);
                    Toast.makeText(getActivity(), "프로필 사진이 수정되었습니다.", Toast.LENGTH_SHORT).show();

                } catch (FileNotFoundException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } catch (IOException e) {

                    // TODO Auto-generated catch block

                    e.printStackTrace();

                } catch (Exception e)

                {

                    e.printStackTrace();

                }

            }

        }

    }


    public String getImageNameToUri(Uri data)

    {

        String[] proj = { MediaStore.Images.Media.DATA };

        Cursor cursor = getActivity().managedQuery(data, proj, null, null, null);

        int column_index = cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);



        cursor.moveToFirst();



        String imgPath = cursor.getString(column_index);

        String imgName = imgPath.substring(imgPath.lastIndexOf("/")+1);



        return imgName;

    }



}


