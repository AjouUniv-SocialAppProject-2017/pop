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
import android.widget.Adapter;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import static android.R.id.edit;
import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.kyu.sap.JoinActivity.UserRef;
import static com.example.kyu.sap.JoinActivity.ds;
import static com.example.kyu.sap.MainActivity.search;
import static com.example.kyu.sap.R.id.editText;
import static com.example.kyu.sap.R.id.et_join_email;
import static com.example.kyu.sap.R.id.et_join_id;
import static com.example.kyu.sap.R.id.et_join_major;
import static com.example.kyu.sap.R.id.et_join_name;
import static com.example.kyu.sap.R.id.et_join_phone_num;
import static com.example.kyu.sap.R.id.et_join_pw;
import static com.example.kyu.sap.R.id.pj_name;
import static com.example.kyu.sap.R.id.uni_txt;
import static com.example.kyu.sap.RegProjectActivity.ds2;

/**
 * Created by Kyu on 2017-11-07.
 */

public class TimeLineFragment extends Fragment{

    watch watcher;
    Handler mhandler;
    static ArrayList<Data> item_list = new ArrayList<>();

    //static myAdapter Adapter;

    //추가
    public static DatabaseReference DataRef = FirebaseDatabase.getInstance().getReference().child("data");




    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //ImageView imageView = (ImageView) getView().findViewById(R.id.foo);
        Log.d("onCreate" , "onCreate");
        Log.d("onCreate" , "onCreate");
        Log.d("onCreate" , "onCreate");

        //item_list.clear();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        Log.d("onAttach" , "onAttach");
        Log.d("onAttach" , "onAttach");
        Log.d("onAttach" , "onAttach");
    }

    @Override
    public void onStart() {
        super.onStart();
        Log.d("onStart" , "onStart");
        Log.d("onStart" , "onStart");
        Log.d("onStart" , "onStart");
    }

    @Override
    public void onResume() {
        super.onResume();
        Log.d("onResume" , "onResume");
        Log.d("onResume" , "onResume");
        Log.d("onResume" , "onResume");
        //Adapter.notifyDataSetChanged();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_blank, null);


        Log.d("onCreateView" , "onCreateView");
        Log.d("onCreateView" , "onCreateView");
        Log.d("onCreateView" , "onCreateView");

        //final myAdapter
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

    /////////////////////////////////

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

            //?????????????????????적으면xx
            //components_list = searched_list;
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
                    intent.putExtra("face_img", data.getFace_img());
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

        public void addProject(Data data){
            searched_list.add(data);
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










