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
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    public static ArrayList<Data> item_list = new ArrayList<>();
    static public String search = "";

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main_Resume", "main_Resume");
        Log.d("main_Resume", "main_Resume");
        Log.d("main_Resume", "main_Resume");
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
        adapter.addFragment(new TimeLineFragment(), null);
        adapter.addFragment(new LikeFragment(), null);
        adapter.addFragment(new MyFragment(), null);
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
}












