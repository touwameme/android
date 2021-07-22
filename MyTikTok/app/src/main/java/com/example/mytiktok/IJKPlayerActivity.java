package com.example.mytiktok;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.SurfaceHolder;
import android.view.View;
import android.view.WindowManager;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mytiktok.player.VideoPlayerIJK;
import com.example.mytiktok.player.VideoPlayerListener;

import tv.danmaku.ijk.media.player.IjkMediaPlayer;

public class IJKPlayerActivity extends AppCompatActivity {
    private VideoPlayerIJK ijkPlayer;
    private MediaPlayer player;
    private SurfaceHolder holder;
    private SeekBar seekBarVideo;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ijkplayer);
        Intent intent = getIntent();
        String path = intent.getStringExtra("feedurl");

        WindowManager.LayoutParams attrs = getWindow().getAttributes();
        attrs.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;
        getWindow().setAttributes(attrs);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        getSupportActionBar().hide();

        seekBarVideo = findViewById(R.id.seekBarVideo);
        seekBarVideo.setVisibility(View.INVISIBLE);
        ijkPlayer = findViewById(R.id.ijkPlayer);


        //加载native库
        try {
            IjkMediaPlayer.loadLibrariesOnce(null);
            IjkMediaPlayer.native_profileBegin("libijkplayer.so");


        } catch (Exception e) {
            this.finish();
        }
        ijkPlayer.setListener(new VideoPlayerListener());
        ijkPlayer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(ijkPlayer.isPlaying()){ijkPlayer.pause();}
                else{
                    ijkPlayer.start();
                }


                if (seekBarVideo.getVisibility() == View.VISIBLE) {
                    seekBarVideo.setVisibility(View.INVISIBLE);
                } else {
                    seekBarVideo.setVisibility(View.VISIBLE);
                    handler.postDelayed(runnable, 3000);
                }
            }
        });
        ijkPlayer.setVideoPath(path);
        seekBarVideo = findViewById(R.id.seekBarVideo);
        handler.postDelayed(runnable, 3000);
        seekBarVideo.setMax(1000);
        ijkPlayer.seekTo(0);
        handler.sendEmptyMessageDelayed(0, 200);
        seekBarVideo.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {
                handler.removeCallbacksAndMessages(null);
            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {
                ijkPlayer.seekTo(seekBar.getProgress() * ijkPlayer.getDuration() / seekBar.getMax());
                handler.sendEmptyMessageDelayed(0, 200);
                handler.postDelayed(runnable, 3000); // 3秒后seekBar消失
            }
        });
    }
    private void updateTime(TextView view, long totalSeconds) {
        long seconds = totalSeconds % 60;
        long minutes = (totalSeconds / 60) % 60;
        long hours = totalSeconds / 3600;
        @SuppressLint("DefaultLocale") String time =
                hours > 0 ? String.format("%02d:%02d:%02d", hours, minutes, seconds)
                        : String.format("%02d:%02d", minutes, seconds);
        view.setText(time);
    }

    Runnable runnable = new Runnable() {
        @Override
        public void run() {
            try {
                seekBarVideo.setVisibility(View.INVISIBLE);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    Handler handler = new Handler() {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case 0:
                    if (ijkPlayer.getDuration() > 0) {
                        seekBarVideo.setProgress(
                                (int) (ijkPlayer.getCurrentPosition() * seekBarVideo.getMax() / ijkPlayer
                                        .getDuration()));
                    }
                    this.sendEmptyMessageDelayed(0, 200);
                    break;
            }
        }
    };
    private String getVideoPath() {
        return "http://qthttp.apple.com.edgesuite.net/1010qwoeiuryfg/sl.m3u8";
//        return "android.resource://" + this.getPackageName() + "/" + resId;
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (ijkPlayer.isPlaying()) {
            ijkPlayer.stop();
        }
        IjkMediaPlayer.native_profileEnd();

    }
}
