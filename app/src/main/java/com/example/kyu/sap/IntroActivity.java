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



        item_list.add(new Data("Place Of Passion","아주대학교","미디어학과","졸업작품 정보 제공 서비스",R.drawable.team1, R.drawable.face, "https://drive.google.com/open?id=1uazGZyGqD1n0GChyeeGBQlCdd6xYQFXQ","https://drive.google.com/open?id=1uCh-iIt-G_thtgLabtQzQI6oidHczB6Q",true,217));
        item_list.get(0).addMember("김규서");
        item_list.get(0).addMember("황선욱");
        item_list.get(0).addTech("#탭레이아웃");
        item_list.get(0).addTech("#파이어베이스");
        item_list.get(0).addTech("#안드로이드");

        item_list.add(new Data("Cat character","아주대학교","미디어학과","그래픽 디자인 캐릭터 모델링 작품",R.drawable.cat2, R.drawable.face2, "https://drive.google.com/open?id=1uazGZyGqD1n0GChyeeGBQlCdd6xYQFXQ","https://drive.google.com/open?id=18vmM7JL3OqKjgMxhtJg44f32Y4__YErO",true,191));
        item_list.get(1).addMember("김규서");
        item_list.get(1).addMember("한단비");
        item_list.get(1).addMember("사랑비");
        item_list.get(1).addTech("#마야");
        item_list.get(1).addTech("#모델링");
        item_list.get(1).addTech("#캐릭터");

        item_list.add(new Data("봐방","아주대학교","미디어학과","방구하기 어플리케이션 서비스",R.drawable.team2__, R.drawable.face3, "https://drive.google.com/open?id=1uazGZyGqD1n0GChyeeGBQlCdd6xYQFXQ","https://drive.google.com/open?id=18vmM7JL3OqKjgMxhtJg44f32Y4__YErO",true,131));
        item_list.get(2).addMember("김규서");
        item_list.get(2).addMember("황선욱");
        item_list.get(2).addTech("Google Map");
        item_list.get(2).addTech("#파이어베이스");
        item_list.get(2).addTech("#안드로이드");

        item_list.add(new Data("디자인 데이터시각화","아주대학교","미디어학과","디자인텍스트 웹크롤링 및 데이터시각화",R.drawable.team4, R.drawable.face4, "https://drive.google.com/open?id=1uazGZyGqD1n0GChyeeGBQlCdd6xYQFXQ","https://drive.google.com/open?id=18vmM7JL3OqKjgMxhtJg44f32Y4__YErO",true,49));
        item_list.get(3).addMember("나동혁");
        item_list.get(3).addMember("김성훈");
        item_list.get(3).addMember("이건우");
        item_list.get(3).addTech("#데이터마이닝");
        item_list.get(3).addTech("#R");
        item_list.get(3).addTech("#데이터시각화");

        item_list.add(new Data("아두이노 크레인","아주대학교","미디어학과","아두이노를 활용한 미니 크레인",R.drawable.team5, R.drawable.profileimage, "https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",true,88));
        item_list.get(4).addMember("박인규");
        item_list.get(4).addMember("이한성");
        item_list.get(4).addMember("정종문");
        item_list.get(4).addTech("#아두이노");
        item_list.get(4).addTech("#3D 프린터");
        item_list.get(4).addTech("#IOT");


    }
}




