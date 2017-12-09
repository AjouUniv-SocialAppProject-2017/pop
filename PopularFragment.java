package com.example.kyu.sap;


import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.kyu.sap.DetailActivity;
import com.example.kyu.sap.R;

import java.util.ArrayList;

import static com.example.kyu.sap.R.id.first_project;
import static com.example.kyu.sap.TimeLineFragment.item_list;

public class PopularFragment extends Fragment{

    //랭킹 저장
    //static ArrayList<Data> item_list_ranking = new ArrayList<>();

    //비교할 NumberofLike를 담고있는 int[]
    private int[] ranking = new int[item_list.size()];

    private int[] ranking_change = new int[item_list.size() * 2];

    //비교하여 NumberOfLike의 index를 저장하는 int[]
    private int[] ranking_index = new int[item_list.size()];


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        int temp = 0;

        Log.d("popular_oncreate", "popular_oncreate");
        Log.d("popular_oncreate", "popular_oncreate");
        Log.d("popular_oncreate", "popular_oncreate");

        //ranking int배열에 numberofLike개수 복사
        for(int i = 0; i< item_list.size(); i++){
            ranking[i] = item_list.get(i).getNumber_of_like();
        }

        // i = 0,1,2,..
        for(int i = 0; i< item_list.size(); i++){
            ranking_change[i * 2] = i;
            ranking_change[(i*2)+1] = ranking[i];
        }

        //Selection Sort
        for(int i = 0; i < ranking.length-1; i++) {
            int min = i;

            for(int j = i + 1; j < ranking.length; j++) {
                if(ranking[j] < ranking[min])
                    min = j;
            }

            temp = ranking[i];
            ranking[i] = ranking[min];
            ranking[min] = temp;
        }

        //index 순위
        for(int i = 0; i < ranking.length; i++){

            int cc = ranking[i];

            for(int j = 0; j < ranking.length; j++){
                if(cc == ranking_change[(j*2)+1]){
                    ranking_index[i] = ranking_change[j*2];
                }
            }
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.popular, null);

        View.OnClickListener detail1 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                final Data data = item_list.get(ranking_index[ranking_index.length-1]);
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

        ImageView first = (ImageView) view.findViewById(first_project);
        first.setOnClickListener(detail1);

        View.OnClickListener detail2 = new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                final Data data = item_list.get(ranking_index[ranking_index.length-2]);
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
                /*
                Toast.makeText(getActivity(),ranking_index.length +"배열의 길이는 : "
                                + ranking_index[0] + ranking_index[1]
                                + ranking_index[2] + ranking_index[3] ,
                        Toast.LENGTH_LONG).show();
                */

                Intent intent = new Intent(getActivity(), DetailActivity.class);
                final Data data = item_list.get(ranking_index[ranking_index.length-3]);
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

        //1등 프로젝트 랭킹 이미지 세팅
        ImageView first_project_image = (ImageView) view.findViewById(R.id.first_project);
        first_project_image.setImageResource(item_list.get(ranking_index[ranking_index.length-1]).getImg());
        //2등 프로젝트 이미지
        ImageView twice_project_image = (ImageView) view.findViewById(R.id.second_project);
        twice_project_image.setImageResource(item_list.get(ranking_index[ranking_index.length-2]).getImg());
        //3등 프로젝트 이미지
        ImageView third_project_image = (ImageView) view.findViewById(R.id.third_project);
        third_project_image.setImageResource(item_list.get(ranking_index[ranking_index.length-3]).getImg());


        return view;
    }

}

