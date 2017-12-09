package com.example.kyu.sap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.ArrayList;

public class PopularFragment extends Fragment{

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_popular, null);

        View.OnClickListener detail1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                ArrayList<Data> item_list = new ArrayList<>();
                item_list.add(new Data("DolDam","인하대학교","컴퓨터공학과","졸업작품 정보 제공 서비스",R.drawable.logo7,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/user/inhauniversity",true,217));
                item_list.get(0).addMember("안진모");
                item_list.get(0).addMember("주완빈");
                item_list.get(0).addMember("한단비");
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

        ImageView first = (ImageView) view.findViewById(R.id.first_project);
        first.setOnClickListener(detail1);




        View.OnClickListener detail2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                ArrayList<Data> item_list = new ArrayList<>();
                item_list.add(new Data("RETRO","인하대학교","컴퓨터공학과","Deep-Learning을 이용한 게임 플레이",R.drawable.team1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=mxMNkPV5TUQ",false,13));
                item_list.get(0).addMember("심재형");
                item_list.get(0).addMember("임재언");
                item_list.get(0).addMember("원상운");
                item_list.get(0).addTech("#안드로이드");
                item_list.get(0).addTech("#Deep_Learning");
                item_list.get(0).addTech("#인공지능");
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

        ImageView second = (ImageView) view.findViewById(R.id.second_project);
        second.setOnClickListener(detail2);




        View.OnClickListener detail3 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                ArrayList<Data> item_list = new ArrayList<>();
                item_list.add(new Data("EyeTracker","인하대학교","컴퓨터공학과","스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스",R.drawable.team1,"https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ","https://www.youtube.com/watch?v=17kA5VkimdE",false,141));
                item_list.get(0).addMember("박혜렴");
                item_list.get(0).addMember("신동호");
                item_list.get(0).addMember("김현석");
                item_list.get(0).addTech("#안드로이드");
                item_list.get(0).addTech("#카메라");
                item_list.get(0).addTech("#시선추적모듈");
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

        ImageView third = (ImageView) view.findViewById(R.id.third_project);
        third.setOnClickListener(detail3);




        return view;
    }


}

