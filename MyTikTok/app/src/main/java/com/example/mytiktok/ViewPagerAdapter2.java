package com.example.mytiktok;

import android.view.View;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter2 extends FragmentStateAdapter {

    public final List<InfoOfVideo> infoOfVideoList = new ArrayList<>();
    public final List<SubFrameFor2> SubFrameList = new ArrayList<>();
    private MyOnClickListener mCickListener;

    public List<InfoOfVideo> getVideoList(){
        return this.infoOfVideoList;
    }
    public List<SubFrameFor2> getSubFrameList(){
        return this.SubFrameList;
    }
    public ViewPagerAdapter2(@NonNull @NotNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull
    @NotNull
    @Override
    public Fragment createFragment(int position) {
        return new SubFrameFor2(infoOfVideoList.get(position));
    }
    public void setOnItemClickListener(MyOnClickListener listener){
        this.mCickListener=listener;
    }
    @Override
    public int getItemCount() {
        return SubFrameList.size();
    }
    public void setData(List<InfoOfVideo> infoOfVideoLi) {
        for (int i = 0; i < infoOfVideoLi.size(); i++) {
            infoOfVideoList.add(infoOfVideoLi.get(i));
            SubFrameFor2 newFrame =  new SubFrameFor2(infoOfVideoLi.get(i));
            SubFrameList.add(newFrame);
        }
    }
}
