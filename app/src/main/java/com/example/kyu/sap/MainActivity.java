package com.example.kyu.sap;

import android.content.Intent;
import android.graphics.PorterDuff;
import android.support.design.widget.TabLayout;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import static android.icu.lang.UCharacter.GraphemeClusterBreak.T;
import static com.example.kyu.sap.TimeLineFragment.item_list;

public class MainActivity extends AppCompatActivity {

    static public String search = "";

    @Override
    protected void onResume() {
        super.onResume();
        Log.d("main_onResume", "main_onResume");
        Log.d("main_onResume", "main_onResume");
        Log.d("main_onResume", "main_onResume");

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.d("main_onCreate", "main_onCreate");
        Log.d("main_onCreate", "main_onCreate");
        Log.d("main_onCreate", "main_onCreate");


        ViewPager viewPager = (ViewPager) findViewById(R.id.viewpager);
        MyAdapter adapter = new MyAdapter(getSupportFragmentManager());
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
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("main_onDestroy" , "main_onDestroy");
        Log.d("main_onDestroy" , "main_onDestroy");
        Log.d("main_onDestroy" , "main_onDestroy");
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

    public void asdf(){

    }
}












