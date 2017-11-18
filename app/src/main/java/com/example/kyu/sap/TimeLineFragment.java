package com.example.kyu.sap;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import static android.R.id.edit;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.kyu.sap.MainActivity.search;
import static com.example.kyu.sap.R.id.editText;
import static com.example.kyu.sap.R.id.uni_txt;

/**
 * Created by Kyu on 2017-11-07.
 */

public class TimeLineFragment extends Fragment{

    watch watcher;
    Handler mhandler;
    static ArrayList<Data> item_list = new ArrayList<>();

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ImageView imageView = (ImageView) getView().findViewById(R.id.foo);

        item_list.clear();

        item_list.add(new Data("Place Of Passion","아주대학교","미디어학과","졸업작품 정보 제공 서비스",R.drawable.cat1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/user/inhauniversity",true,217));
        item_list.get(0).addMember("김규서");
        item_list.get(0).addMember("황선욱");
        item_list.get(0).addMember("홍길동");
        item_list.get(0).addTech("#리스트뷰");
        item_list.get(0).addTech("#뷰페이저");
        item_list.get(0).addTech("#안드로이드");


        item_list.add(new Data("EyeTracker","아주대학교","미디어학과","스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스",R.drawable.cat2,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",true,141));
        item_list.get(1).addMember("박혜린");
        item_list.get(1).addMember("신동호");
        item_list.get(1).addMember("김현석");
        item_list.get(1).addTech("#안드로이드");
        item_list.get(1).addTech("#카메라");
        item_list.get(1).addTech("#시선추적모듈");
/*
        item_list.add(new Data("망국의 왕자","인하대학교","컴퓨터공학과","유니티 기반 턴제 SRPG",R.drawable.team2,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=dgzdbicePYY",true,38));
        item_list.get(2).addMember("이민영");
        item_list.get(2).addMember("이명호");
        item_list.get(2).addMember("심상형");
        item_list.get(2).addTech("#안드로이드");
        item_list.get(2).addTech("#유니티");
        item_list.get(2).addTech("#SRPG");

        item_list.add(new Data("인하인아","인하대학교","컴퓨터공학과","인하대학교 정보 제공 챗봇 서비스",R.drawable.team3,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=PRHCUOZPe28",true,91));
        item_list.get(3).addMember("윤희수");
        item_list.get(3).addMember("양희정");
        item_list.get(3).addTech("#안드로이드");
        item_list.get(3).addTech("#챗봇");
        item_list.get(3).addTech("#크롤링");

        item_list.add(new Data("Triple Core","인하대학교","컴퓨터공학과","Interior Helper",R.drawable.team4,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=Rg8cqKhb7B0",true,114));
        item_list.get(4).addMember("김형석");
        item_list.get(4).addMember("원정아");
        item_list.get(4).addMember("김민지");
        item_list.get(4).addTech("#안드로이드");
        item_list.get(4).addTech("#카메라");

        item_list.add(new Data("RETRO","인하대학교","컴퓨터공학과","Deep-Learning을 이용한 게임 플레이",R.drawable.team5,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=mxMNkPV5TUQ",false,13));
        item_list.get(5).addMember("심재형");
        item_list.get(5).addMember("임재언");
        item_list.get(5).addMember("원상운");
        item_list.get(5).addTech("#안드로이드");
        item_list.get(5).addTech("#Deep_Learning");
        item_list.get(5).addTech("#인공지능");

        item_list.add(new Data("GEUS","인하대학교","컴퓨터공학과","고지서 나오기 전 예상금액 산출",R.drawable.team6,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=KzJN2fwiTbg",false,62));
        item_list.get(6).addMember("김다빈");
        item_list.get(6).addMember("김영현");
        item_list.get(6).addMember("손예별");
        item_list.get(6).addTech("#안드로이드");
        item_list.get(6).addTech("#카메라");
        item_list.get(6).addTech("#숫자인식");

        item_list.add(new Data("상담 신청이 제일 쉬웠어요","인하대학교","컴퓨터공학과","상담 커뮤니케이션 어플리케이션",R.drawable.team7,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=VWuXOqh8dgw",true,70));
        item_list.get(7).addMember("권철건");
        item_list.get(7).addMember("하준혁");
        item_list.get(7).addMember("땅항렝");
        item_list.get(7).addTech("#안드로이드");
        item_list.get(7).addTech("#서버");
        item_list.get(7).addTech("#데이터베이스");

        item_list.add(new Data("Stepic","인하대학교","컴퓨터공학과","공간 중심의 사진 SNS",R.drawable.team8,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=tMQaIFGt780",true,238));
        item_list.get(8).addMember("한단비");
        item_list.get(8).addMember("손은겸");
        item_list.get(8).addMember("장지은");
        item_list.get(8).addTech("#안드로이드");
        item_list.get(8).addTech("#카메라");
        item_list.get(8).addTech("#SNS");

        item_list.add(new Data("강추","인하대학교","컴퓨터공학과","강의 추천 기반 시간표 사이트",R.drawable.team9,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=NfAYpNKgrYk",false,11));
        item_list.get(9).addMember("이강호");
        item_list.get(9).addMember("한정");
        item_list.get(9).addTech("#안드로이드");
        item_list.get(9).addTech("#웹");
        item_list.get(9).addTech("#데이터베이스");

        item_list.add(new Data("HOBBIT","인하대학교","컴퓨터공학과","NFC를 이용한 스마트콘센트",R.drawable.team10,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=SoGgBVaIaHU",true,82));
        item_list.get(10).addMember("이태규");
        item_list.get(10).addMember("정헌휘");
        item_list.get(10).addMember("김건희");
        item_list.get(10).addTech("#NFC");
        item_list.get(10).addTech("#아두이노");
        item_list.get(10).addTech("#안드로이드");


        item_list.add(new Data("CRIS","인하대학교","컴퓨터공학과","재난 대응 시뮬레이션 게임",R.drawable.team11,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=Kq1ceekG1DM",true,231));
        item_list.get(11).addMember("유승재");
        item_list.get(11).addMember("김승환");
        item_list.get(11).addTech("#안드로이드");
        item_list.get(11).addTech("#VR");
        item_list.get(11).addTech("#블루투스");


        item_list.add(new Data("Comento","인하대학교","컴퓨터공학과","피부 분석 화장품 추천 어플리케이션",R.drawable.team12,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=BhmQU2kgeos",false,7));
        item_list.get(12).addMember("백승환");
        item_list.get(12).addMember("이진아");
        item_list.get(12).addMember("윤찬미");
        item_list.get(12).addTech("#안드로이드");
        item_list.get(12).addTech("#카메라");
        item_list.get(12).addTech("#얼굴인식");
*/
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, null);


        final myAdapter Adapter = new myAdapter(view.getContext(), R.layout.item, item_list);
        ListView list = (ListView)view.findViewById(R.id.lst_work);

        list.setAdapter(Adapter);
        list.setTextFilterEnabled(true);


        View v = inflater.inflate(R.layout.activity_main, container, false);

        //EditText search2 = (EditText) v.findViewById(R.id.editText);


        watcher = new watch(Adapter);
        watcher.start();

        mhandler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what ==0) {
                    Log.e(this.getClass().getName(),"WHAT : 0 "+ search);
                    Adapter.filter(search);
                    Adapter.notifyDataSetChanged();
                }
                else if(msg.what == 2){
                    Adapter.notifyDataSetChanged();
                }
                else{
                    Log.e(this.getClass().getName(),"WHAT : 1 "+msg.obj.toString());
                    Adapter.filter(msg.obj.toString());
                    Adapter.notifyDataSetChanged();
                }
            }
        };
        return view;
    }

    public class myAdapter extends BaseAdapter{
        Context con;
        LayoutInflater inflater;
        ArrayList<Data> components_list; //
        ArrayList<Data> searched_list; //
        int layout;
        myAdapter(Context context, int layout, ArrayList<Data> components_list) {
            con = context;
            this.layout = layout;
            this.components_list = components_list;
            searched_list = new ArrayList<>();
            for(int i=0;i<components_list.size();i++){
                searched_list.add(components_list.get(i));
            }
            inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            // 멤버변수 초기화
        }

        @Override
        public int getCount() {
            // TODO Auto-generated method stub
            return searched_list.size();
        }

        @Override
        public Object getItem(int position) {
            // TODO Auto-generated method stub
            return searched_list.get(position);
        }

        @Override
        public long getItemId(int position) {
            // TODO Auto-generated method stub
            return position;
        }
        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // TODO Auto-generated method stub
            if (null == convertView) {
                convertView = inflater.inflate(layout, parent, false);
                //cells 를 뷰화시켜서 아이템목록으로 삽입

            }

            if(searched_list.size()==0){
                for(int i=0;i<components_list.size();i++){
                    searched_list.add(components_list.get(i));
                }

            }

            final Data data = searched_list.get(position);

            // 이미지 삽입
            ImageView img = (ImageView) convertView.findViewById(R.id.project_img);
            img.setBackground(ContextCompat.getDrawable(convertView.getContext(), data.getImg()));

            // 좋아요 버튼 상태
            final ImageButton like_btn = (ImageButton) convertView.findViewById(R.id.like_btn);
            if (data.isLike()) {
                like_btn.setBackground(ContextCompat.getDrawable(convertView.getContext(), R.drawable.heart_on));
            } else {
                like_btn.setBackground(ContextCompat.getDrawable(convertView.getContext(), R.drawable.heart_off));
            }

            // 이미지 클릭시 디테일 페이지 이동
            img.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(getActivity(), DetailActivity.class);
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
            });

            // 좋아요 버튼 클릭
            final View finalConvertView = convertView;
            like_btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    data.setLike(!data.isLike());
                    if (data.isLike()) {
                        like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(), R.drawable.heart_on));
                        data.setNumber_of_like(data.getNumber_of_like()+1);
                    } else {
                        like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(), R.drawable.heart_off));
                        data.setNumber_of_like(data.getNumber_of_like()-1);

                    }

                    mhandler.sendEmptyMessage(2);
                }
            });


            // 프로젝트 이름
            TextView pj_name = (TextView) convertView.findViewById(R.id.pj_name_txt);
            pj_name.setText(data.getPj_name());



            // 학교
            //TextView uni_txt = (TextView) convertView.findViewById(R.id.uni_txt);
            //uni_txt.setText(data.getUniversity());

            // 학과
            //TextView major = (TextView) convertView.findViewById(R.id.major);
            //major.setText(data.getMajor());

            // 맴버
            TextView member = (TextView) convertView.findViewById(R.id.member1);
            String _member = "";
            for (int i = 0; i < data.memberLength(); i++) {
                _member += data.getMember(i);
                _member += " ";
            }
            member.setText(_member);

            // 요약
            TextView summary = (TextView) convertView.findViewById(R.id.summary_txt);
            summary.setText(data.getSummary());

            //좋아요 숫자
            TextView numbers_of_likt = (TextView)convertView.findViewById(R.id.numbers_of_like);
            numbers_of_likt.setText(String.valueOf(data.getNumber_of_like())+"명");


            // 기술
            LinearLayout tech_layout = (LinearLayout) convertView.findViewById(R.id.tech_layout);
            final ArrayList<TextView> tech = new ArrayList<>();
            tech_layout.removeAllViews();

            for (int i = 0; i < data.techLength(); i++) {
                tech.add(new TextView(getActivity()));
                tech.get(i).setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                tech.get(i).setTextSize(15);
                tech.get(i).setTextColor(Color.BLUE);
                tech.get(i).setText(data.getTech(i) + " ");
                tech_layout.addView(tech.get(i));
                final int finalI = i;
                tech.get(i).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        //헤시테그
                        //MainActivity.search = tech.get(finalI).getText().toString();
                        String temp = tech.get(finalI).getText().toString();
                        Toast.makeText(getContext(), temp, Toast.LENGTH_SHORT).show();
                        Message msg = mhandler.obtainMessage();
                        msg.what = 1;
                        msg.obj = temp;
                        mhandler.sendMessage(msg);
                    }
                });
            }

            return convertView;
        }


        public void filter(String search) {
            Log.e(this.getClass().getName(),"filter start");
            searched_list.clear();
            if (search == "") {
                Log.e(this.getClass().getName(),"No Input");
                for(int i=0;i<components_list.size();i++){
                    searched_list.add(components_list.get(i));
                }
            }

            Data temp;

            for (int i = 0; i < components_list.size(); i++) {
                temp = components_list.get(i);
                String all = "";
                all += temp.getPj_name();
                all += " ";
                all += temp.getUniversity();
                all += " ";
                all += temp.getMajor();
                all += " ";
                for (int j = 0; j < temp.memberLength(); j++) {
                    all += temp.getMembers().get(j).toString();
                    all += " ";
                }
                all += temp.getSummary();
                all += " ";
                for (int j = 0; j < temp.techLength(); j++) {
                    all += temp.getTechs().get(j).toString();
                    all += " ";
                }

                Log.e(this.getClass().getName(),all);

                if (all.contains(search)) {
                    searched_list.add(temp);
                }
            }

//            this.notifyDataSetChanged();
//
            for(int i=0;i<searched_list.size();i++){
                Log.e(this.getClass().getName(),searched_list.get(i).getPj_name().toString());
            }
        }

        public void filter2(String search) {
            Log.e(this.getClass().getName(),"filter2 start");
            searched_list.clear();
            if (search == "") {
                Log.e(this.getClass().getName(),"No Input");
                for(int i=0;i<components_list.size();i++){
                    searched_list.add(components_list.get(i));
                }
            }

            Data temp;

            for (int i = 0; i < components_list.size(); i++) {
                temp = components_list.get(i);
                for (int j = 0; j < temp.techLength(); j++) {
                    if(temp.getTech(j).contains(search)){
                        searched_list.add(temp);
                        Log.e(this.getClass().getName(),String.valueOf(searched_list.size()));
                        break;
                    }
                }
            }

//            this.notifyDataSetChanged();
//
            for(int i=0;i<searched_list.size();i++){
                Log.e(this.getClass().getName(),searched_list.get(i).getPj_name().toString());
            }
        }
    }

    class watch extends Thread{
        myAdapter adapter;
        String str;

        public watch(myAdapter adapter){
            this.adapter = adapter;
            str = "";
        }

        public void run(){
            while (true){
                try {
                    sleep(500);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                if(str!= search){
                    str = search;
                    Log.e(this.getClass().getName(), search);
                    mhandler.sendEmptyMessage(0);
                }
            }
        }
    }

}










