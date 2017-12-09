package com.example.kyu.sap;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import static com.example.kyu.sap.TimeLineFragment.item_list;

/**
 * Created by Kyu on 2017-11-08.
 */

public class IntroActivity extends AppCompatActivity {

    Intent intent;
    Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.intro);

        handler = new Handler();


        item_list.add(new Data("Place Of Passion","아주대학교","미디어학과","졸업작품 정보 제공 서비스",R.drawable.cat3,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/user/ajouuniversity",true,217));
        item_list.get(0).addMember("0번째");
        item_list.get(0).addMember("0번째");
        item_list.get(0).addMember("0번째");
        item_list.get(0).addTech("#리스트뷰");
        item_list.get(0).addTech("#뷰페이저");
        item_list.get(0).addTech("#안드로이드");

        item_list.add(new Data("EyeTracker","아주대학교","미디어학과","스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스",R.drawable.cat2,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",true,141));
        item_list.get(1).addMember("1번째");
        item_list.get(1).addMember("1번째");
        item_list.get(1).addMember("1번째");
        item_list.get(1).addTech("#안드로이드");
        item_list.get(1).addTech("#카메라");
        item_list.get(1).addTech("#시선추적모듈");

        item_list.add(new Data("EyeTracker","아주대학교","미디어학과","스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스",R.drawable.cat1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",true,35));
        item_list.get(2).addMember("2번째");
        item_list.get(2).addMember("2번째");
        item_list.get(2).addMember("2번째");
        item_list.get(2).addTech("#안드로이드");
        item_list.get(2).addTech("#카메라");
        item_list.get(2).addTech("#시선추적모듈");

        item_list.add(new Data("EyeTracker","아주대학교","미디어학과","스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스",R.drawable.cat1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",true,70));
        item_list.get(3).addMember("3번째");
        item_list.get(3).addMember("3번째");
        item_list.get(3).addMember("3번째");
        item_list.get(3).addTech("#안드로이드");
        item_list.get(3).addTech("#카메라");
        item_list.get(3).addTech("#시선추적모듈");


        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(1500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                intent = new Intent(IntroActivity.this, LoginActivity.class);
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        startActivity(intent);
                        overridePendingTransition(R.anim.fade_in, R.anim.fade_out);
                        finish();
                    }
                });
            }
        }).start();


    }
}




