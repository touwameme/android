package com.example.mytiktok;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter{
    public   MainFragment mainFragment= new MainFragment();
    public   Fragment2 fragment2 = new Fragment2();
    public   Fragment3 fragment3 = new Fragment3();

    public PagerAdapter(@NonNull  FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:
                return mainFragment;
            case 1:
                return  fragment2;

            case 2:
                return fragment3;
            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}