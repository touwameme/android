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

    private MyOnClickListener mListener;
    public void bind(final InfoOfVideo infoOfVideo) {
        nickName.setText(infoOfVideo.nickName);
        imageView.setImageURL(infoOfVideo.thumbnails); //cover_img
        likeCount.setText("likes：" + infoOfVideo.likeCount);
        descp.setText(infoOfVideo.description);
    }

    public ListHolder(@NonNull View itemView, MyOnClickListener listener) {
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
