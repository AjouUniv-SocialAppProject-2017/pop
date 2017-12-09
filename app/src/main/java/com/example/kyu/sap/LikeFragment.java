package com.example.kyu.sap;


import android.content.Context;
import android.content.Intent;
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
import android.widget.GridView;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;
import static com.example.kyu.sap.TimeLineFragment.item_list;


public class LikeFragment extends Fragment{
    Handler mhandler;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.like, null);



        final myAdapter Adapter = new myAdapter(view.getContext(), R.layout.image_item, item_list);
        GridView list = (GridView) view.findViewById(R.id.Grid_image);

        list.setAdapter(Adapter);
        list.setTextFilterEnabled(true);
        Adapter.filter();

        mhandler = new Handler(){
            public void handleMessage(Message msg) {
                if(msg.what ==0) {
                    Adapter.filter();
                    Adapter.notifyDataSetChanged();
                }
            }
        };


        return view;
    }


    public class myAdapter extends BaseAdapter {
        Context con;
        LayoutInflater inflater;
        ArrayList<Data> components_list;
        ArrayList<Data> searched_list;
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
            ImageView img = (ImageView) convertView.findViewById(R.id.item_img);
            img.setBackground(ContextCompat.getDrawable(convertView.getContext(), data.getImg()));

            // 좋아요 버튼 상태
            final ImageButton like_btn = (ImageButton) convertView.findViewById(R.id.item_heart);
            //좋아요 한 상태이면
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
                    //
                    data.setLike(!data.isLike());
                    //좋아요 인 상태이면
                    if (data.isLike()) {
                        like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(), R.drawable.heart_on));
                        data.setNumber_of_like(data.getNumber_of_like()+1);
                    } else {
                        like_btn.setBackground(ContextCompat.getDrawable(finalConvertView.getContext(), R.drawable.heart_off));
                        data.setNumber_of_like(data.getNumber_of_like()-1);
                    }
                    mhandler.sendEmptyMessage(0);
                }
            });



            return convertView;
        }

        public void filter() {
            Log.e(this.getClass().getName(),"filter start");
            searched_list.clear();

            Data temp;

            for (int i = 0; i < components_list.size(); i++) {
                temp = components_list.get(i);
                if(temp.isLike()){
                    searched_list.add(components_list.get(i));
                }
            }

//            this.notifyDataSetChanged();
//
            for(int i=0;i<searched_list.size();i++){
                Log.e(this.getClass().getName(),searched_list.get(i).getPj_name().toString());
            }
        }
    }

}

