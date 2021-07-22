package com.example.mytiktok;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.backends.pipeline.Fresco;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;



public class MainFragment extends Fragment {

    private ListAdapter listAdapter = new ListAdapter();
    private RecyclerView recyclerView;
    private RecyclerView.LayoutManager layoutManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view =inflater.inflate(R.layout.main_fragment, container, false);
        recyclerView = view.findViewById(R.id.recycleVideo);
        layoutManager = new LinearLayoutManager(
                view.getContext(), LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(listAdapter);
        requestData();
        Log.d("FragM",listAdapter.getItemCount()+"\n");
        Fresco.initialize(view.getContext());
        Log.d("FragM","after init fresco");
        listAdapter.setOnItemClickListener(new MyOnClickListener() {
            @Override
            public void onItemClick(View view, int position) {
                Log.d("FragM","onItemClick\n");
                String url = listAdapter.getVideoList().get(position).feedUrl;
                Log.d("FragM","set url:"+url);
                Bundle bundle = new Bundle();
                bundle.putString("feedurl", listAdapter.getVideoList().get(position).feedUrl);
                Intent jmpintent = new Intent(view.getContext(), IJKPlayerActivity.class);
                jmpintent.putExtras(bundle);
                Log.d("FragM","jmpitent");
                startActivity(jmpintent);

            }
        });
        return view ;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        // TODO: Use the ViewModel


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
                Log.d("FragM", "success\n");
                if (response.body() != null) {
                    List<InfoOfVideo> infoOfVideoList = response.body();
                    if (infoOfVideoList.size() != 0) {
                        listAdapter.setData(infoOfVideoList);
                        listAdapter.notifyDataSetChanged();
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