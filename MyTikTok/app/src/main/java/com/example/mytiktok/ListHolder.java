package com.example.mytiktok;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


public class ListHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    private ImageView imageView;
    private TextView nickName;
    private TextView likeCount;
    private TextView descp;

    private OnItemClickListener mListener;
    public void bind(final Video video) {
        nickName.setText(video.nickName);
        imageView.setImageURL(video.thumbnails); //cover_img
        likeCount.setText("likes：" + video.likeCount);
        descp.setText(video.description);
    }

    public ListHolder(@NonNull View itemView, OnItemClickListener listener) {
        super(itemView);
        itemView.setOnClickListener(this);
        imageView = itemView.findViewById(R.id.image);
        nickName = itemView.findViewById(R.id.text_nickname);
        likeCount = itemView.findViewById(R.id.text_likecount);
        descp = itemView.findViewById(R.id.tv_description);
        mListener=listener;
    }



    @Override
    public void onClick(View v) {
        mListener.onItemClick(v,getAdapterPosition());
    }
}
