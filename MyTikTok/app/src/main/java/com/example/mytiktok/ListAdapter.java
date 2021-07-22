package com.example.mytiktok;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListAdapter extends RecyclerView.Adapter<ListHolder> {

    public final List<InfoOfVideo> infoOfVideoList = new ArrayList<>();
    private MyOnClickListener mCickListener;

    public void refresh(List<InfoOfVideo> newInfoOfVideos) {
        infoOfVideoList.clear();
        if (newInfoOfVideos != null) {
            infoOfVideoList.addAll(newInfoOfVideos);
        }
        notifyDataSetChanged();
    }

    public List<InfoOfVideo> getVideoList(){
        return this.infoOfVideoList;
    }

    @NonNull
    @Override
    public ListHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.video_item, parent, false);
        return new ListHolder(itemView,mCickListener);
    }

    public void setOnItemClickListener(MyOnClickListener listener){
        this.mCickListener=listener;
    }
    @Override
    public void onBindViewHolder(@NonNull ListHolder holder, int position) {
        holder.bind(infoOfVideoList.get(position));
    }

    @Override
    public int getItemCount() {
        return infoOfVideoList.size();
    }

    public void setData(List<InfoOfVideo> infoOfVideoLi) {
        for (int i = 0; i < infoOfVideoLi.size(); i++) {
            infoOfVideoList.add(infoOfVideoLi.get(i));
        }
    }
}

