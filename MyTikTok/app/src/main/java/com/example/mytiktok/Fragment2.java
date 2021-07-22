package com.example.mytiktok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import com.facebook.drawee.backends.pipeline.Fresco;

import org.jetbrains.annotations.NotNull;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class Fragment2 extends Fragment {



    private ViewPager2 viewPager;
    private ViewPagerAdapter2 FrameAdapter ;

    public Fragment2() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view =inflater.inflate(R.layout.layout_viewpager2, container, false);
        FrameAdapter= new  ViewPagerAdapter2(getActivity());

        viewPager = view.findViewById(R.id.ViewPager2);
        requestData();
        viewPager.setAdapter(FrameAdapter);

        Fresco.initialize(view.getContext());
        return view;

    }

    private void requestData() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://beiyou.bytedance.com/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        ApiService apiService = retrofit.create(ApiService.class);
        apiService.getVideos().enqueue(new Callback<List<InfoOfVideo>>() {
            @Override
            public void onResponse(Call<List<InfoOfVideo>> call, Response<List<InfoOfVideo>> response) {
                if (response.body() != null) {
                    List<InfoOfVideo> infoOfVideoList = response.body();
                    if (infoOfVideoList.size() != 0) {
                        Log.d("FRAG2", infoOfVideoList.get(0).feedUrl);
                        FrameAdapter.setData(infoOfVideoList);
                        FrameAdapter.notifyDataSetChanged();
                    }
                }
            }
            @Override
            public void onFailure(Call<List<InfoOfVideo>> call, Throwable t) {
                //System.err.println(call.toString());
                Log.d("retrofitFail", t.getMessage());
            }
        });

    }


}



