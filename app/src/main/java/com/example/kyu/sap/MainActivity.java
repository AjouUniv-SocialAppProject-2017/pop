package com.example.kyu.sap;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    static public String search = "";
    static ArrayList<Data> item_list = new ArrayList<>();
    private DatabaseReference projRef= FirebaseDatabase.getInstance().getReference().child("projects");
    private DatabaseReference MyRef= FirebaseDatabase.getInstance().getReference();
    public  MyAdapter adapter = new MyAdapter(getSupportFragmentManager());

    public static DataSnapshot ds2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);

        adapter.addFragment(new TimeLineFragment(), null);
        adapter.addFragment(new LikeFragment(), null);
        adapter.addFragment(new PopularFragment(), null);
        adapter.addFragment(new ProfileFragment(), null);

        viewPager.setAdapter(adapter);
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(viewPager);

        tabLayout.getTabAt(0).setIcon(R.drawable.timeline);
        tabLayout.getTabAt(1).setIcon(R.drawable.heart);
        tabLayout.getTabAt(2).setIcon(R.drawable.star);
        tabLayout.getTabAt(3).setIcon(R.drawable.profile);


        TabLayout.Tab tab = tabLayout.getTabAt(0);
        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);

        tabLayout.setOnTabSelectedListener(
                new TabLayout.ViewPagerOnTabSelectedListener(viewPager){
                    @Override
                    public void onTabSelected(TabLayout.Tab tab) {
                        super.onTabSelected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.colorPrimary);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }
                    @Override
                    public void onTabUnselected(TabLayout.Tab tab) {
                        super.onTabUnselected(tab);
                        int tabIconColor = ContextCompat.getColor(getApplicationContext(), R.color.inActive);
                        tab.getIcon().setColorFilter(tabIconColor, PorterDuff.Mode.SRC_IN);
                    }
                    @Override
                    public void onTabReselected(TabLayout.Tab tab) {
                        super.onTabReselected(tab);
                    }
                }
        );

        final EditText edittext = (EditText)findViewById(R.id.editText);
        edittext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                search = edittext.getText().toString();
            }
        });




        item_list.clear();


        writeNewProject("DolDam", "아주대학교", "컴퓨터공학과", "졸업작품 정보 제공 서비스", R.drawable.logo7, "https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ", "https://www.youtube.com/user/inhauniversity", true, 217);
        writeNewProject("RETRO", "아주대학교", "컴퓨터공학과", "Deep-Learning을 이용한 게임 플레이", R.drawable.team1, "https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ", "https://www.youtube.com/watch?v=mxMNkPV5TUQ", false, 13);
        writeNewProject("EyeTracker", "아주대학교", "컴퓨터공학과", "스마트 폰의 전면 카메라를 이용한 시선 추적 인터페이스", R.drawable.team1, "https://drive.google.com/open?id=0B8gBCAmXbA4VQWZjOUxfZlMwaDQ", "https://www.youtube.com/watch?v=17kA5VkimdE", false, 141);

//        ranking();



        ValueEventListener UserListener = new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // Get Post object and use the values to update the UI

                Set<Data> set_proj=new HashSet<Data>();

                Iterator i = dataSnapshot.getChildren().iterator();

                Data imsi = new Data();
                String imsi_name = "";
                String imsi_major = "";

                //ArrayList Popular<Data>
                //int[]

                while (i.hasNext()) {

                    imsi = (((DataSnapshot) i.next()).getValue(Data.class));

                    imsi_name = imsi.getPj_name();
                    imsi_major = imsi.getMajor();
                    //~~~~~~~~~~~~다가져와

                    //set_proj.add(((DataSnapshot) i.next()).getValue(Data.class));

                    //Popular<Data>  = 넣어

                }

                //비교

                Toast.makeText(getApplicationContext(), imsi_name + ", " + imsi_major, Toast.LENGTH_SHORT).show();

                //item_list.addAll(set_proj);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                // Getting Post failed, log a message

                // ...
            }
        };
        projRef.addValueEventListener(UserListener);


    }


    private void writeNewProject(String pj_name, String university, String major, String summary, int img, String presentation, String video, boolean like, int number_of_like) {


        String key = MyRef.child("projects").push().getKey();
        Data proj = new Data(pj_name, university, major, summary, img, presentation, video, like, number_of_like);
        Map<String, Object> userValues = proj.toMap();

        Map<String, Object> childUpdates = new HashMap<>();
        childUpdates.put("/projects/" + key, userValues);


        MyRef.updateChildren(childUpdates);

//        arrayAdapter.notifyDataSetChanged();
    }


    private void ranking() {



        Set<Data> set_proj = new HashSet<Data>();

        Iterator i = ds2.getChildren().iterator();

        while (i.hasNext()) {
            set_proj.add(((DataSnapshot) i.next()).getValue(Data.class));

        }

        item_list.clear();

        item_list.addAll(set_proj);


    }
    public void mOnClick(View v){
        switch(v.getId()){
            case R.id.add_project:
                Intent intent = new Intent(this, RegProjectActivity.class);
                startActivity(intent);
                break;
            case R.id.p_logo:
                Intent intent2 = new Intent(this, UseRule.class);
                startActivity(intent2);
                break;
        }
    }

//    public void writeNewProject(String pj_name,String university,String major,String summary,int img,String presentation,String video,boolean like,int number_of_like) {
//
//
//        String key=MyRef.child("projects").push().getKey();
//        Data proj=new Data(pj_name,university,major,summary,img,presentation,video,like,number_of_like);
//        Map<String, Object> userValues = proj.toMap();
//
//        Map<String, Object> childUpdates = new HashMap<>();
//        childUpdates.put("/projects/"+key, userValues);
//
//
//        MyRef.updateChildren(childUpdates);
//
////        arrayAdapter.notifyDataSetChanged();
//    }


}












