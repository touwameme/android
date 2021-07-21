package com.example.mytiktok;

import android.os.Bundle;
import android.graphics.Color;

import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;


public class MainActivity extends AppCompatActivity {
    private ListAdapter listAdapter = new ListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        TextView tv_item_one = findViewById(R.id.tv_item_one);
        TextView tv_item_two = findViewById(R.id.tv_item_two);
        TextView tv_item_three = findViewById(R.id.tv_item_three);
        ViewPager myViewPager = findViewById(R.id.myViewPager);

        tv_item_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(0);
                tv_item_one.setTextColor(Color.WHITE);
                tv_item_two.setTextColor(Color.GRAY);
                tv_item_three.setTextColor(Color.GRAY);
            }
        });

        tv_item_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(1);
                tv_item_one.setTextColor(Color.GRAY);
                tv_item_two.setTextColor(Color.WHITE);
                tv_item_three.setTextColor(Color.GRAY);
            }
        });
        tv_item_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(2);
                tv_item_one.setTextColor(Color.GRAY);
                tv_item_two.setTextColor(Color.GRAY);
                tv_item_three.setTextColor(Color.WHITE);
            }
        });


        myViewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                switch (position){
                    case 0:{
                        tv_item_one.setTextColor(Color.WHITE);
                        tv_item_two.setTextColor(Color.GRAY);
                        tv_item_three.setTextColor(Color.GRAY);
                        break;
                    }
                    case 1:{
                        tv_item_one.setTextColor(Color.GRAY);
                        tv_item_two.setTextColor(Color.WHITE);
                        tv_item_three.setTextColor(Color.GRAY);
                        break;
                    }
                    case 2:{
                        tv_item_one.setTextColor(Color.GRAY);
                        tv_item_two.setTextColor(Color.GRAY);
                        tv_item_three.setTextColor(Color.WHITE);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        getSupportActionBar().hide();
        myViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        myViewPager.setCurrentItem(0);
    }


}
