package com.example.mytiktok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link Fragment3#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SubFrameFor2 extends Fragment {

    private ImageView imageView;
    private TextView nickName;
    private TextView likeCount;
    private TextView descp;
    private InfoOfVideo videoinfo;



    public SubFrameFor2(InfoOfVideo infoOfVideo) {
        this.videoinfo = infoOfVideo;
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.layout_subframe2, container, false);
        imageView = view.findViewById(R.id.image);
        nickName = view.findViewById(R.id.text_nickname);
        likeCount = view.findViewById(R.id.text_likecount);
        descp = view.findViewById(R.id.tv_description);
        nickName.setText(this.videoinfo.nickName);
        likeCount.setText("likes:"+this.videoinfo.likeCount);
        descp.setText(this.videoinfo.description);
        imageView.setImageURL(this.videoinfo.thumbnails);
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("feedurl", videoinfo.feedUrl);
                Intent jmpintent = new Intent(view.getContext(), IJKPlayerActivity.class);
                jmpintent.putExtras(bundle);
                Log.d("FRAG2","jmpitent");
                startActivity(jmpintent);

            }

        });
        return view;
    }

}