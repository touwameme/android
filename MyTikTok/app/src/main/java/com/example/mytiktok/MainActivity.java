package com.example.mytiktok;


import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;

import android.view.SurfaceHolder;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager.widget.ViewPager;




public class MainActivity extends FragmentActivity {

    private ListAdapter listAdapter = new ListAdapter();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main_activity);
        Window window = this.getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);

        TextView tv_item_one = findViewById(R.id.tv_item_one);
        TextView tv_item_two = findViewById(R.id.tv_item_two);
        TextView tv_item_three = findViewById(R.id.tv_item_three);
        ViewPager myViewPager = findViewById(R.id.myViewPager);

        tv_item_one.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(0);
                tv_item_one.setTextColor(Color.BLACK);
                tv_item_two.setTextColor(Color.GRAY);
                tv_item_three.setTextColor(Color.GRAY);
            }
        });

        tv_item_two.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(1);
                tv_item_one.setTextColor(Color.GRAY);
                tv_item_two.setTextColor(Color.BLACK);
                tv_item_three.setTextColor(Color.GRAY);
            }
        });
        tv_item_three.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myViewPager.setCurrentItem(2);
                tv_item_one.setTextColor(Color.GRAY);
                tv_item_two.setTextColor(Color.GRAY);
                tv_item_three.setTextColor(Color.BLACK);
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
                        tv_item_one.setTextColor(Color.BLACK);
                        tv_item_two.setTextColor(Color.GRAY);
                        tv_item_three.setTextColor(Color.GRAY);
                        break;
                    }
                    case 1:{
                        tv_item_one.setTextColor(Color.GRAY);
                        tv_item_two.setTextColor(Color.BLACK);
                        tv_item_three.setTextColor(Color.GRAY);
                        break;
                    }
                    case 2:{
                        tv_item_one.setTextColor(Color.GRAY);
                        tv_item_two.setTextColor(Color.GRAY);
                        tv_item_three.setTextColor(Color.BLACK);
                        break;
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

     //   getSupportActionBar().hide();
        myViewPager.setAdapter(new PagerAdapter(getSupportFragmentManager()));
        myViewPager.setCurrentItem(0);

    }
    }

