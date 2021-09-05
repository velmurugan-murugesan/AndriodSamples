package com.example.company.playondemand.activity;

import android.app.Activity;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.MediaController;
import android.widget.VideoView;

import com.example.company.playondemand.R;

import java.io.IOException;

/**
 * Created by velmmuru on 9/5/2016.
 */
public class VideoActivity extends Activity {

    String path = null;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.video_activity);

        Bundle getBundle = this.getIntent().getExtras();
        path = getBundle.getString("path");

        if(path != null && path.length() > 0){
            Log.d("Video Activity Path ",path);
            VideoView view = (VideoView)findViewById(R.id.videoView);
            Log.d("path--------------", getPackageName());
            Log.d("path", getPackageName());
            Uri uri = Uri.parse(path);
            MediaController mc = new MediaController(this);
            mc.setAnchorView(view);
            mc.setMediaPlayer(view);
            mc.setVisibility(View.VISIBLE);
            view.setMediaController(mc);
            view.setOnPreparedListener(PreparedListener);
            view.setVideoURI(uri);
            view.requestFocus();
            view.start();
        }


        /*AssetFileDescriptor descriptor = null;
        try {
            descriptor = this.getAssets().openFd("kkk");
        } catch (IOException e) {
            e.printStackTrace();
        }
        long start = descriptor.getStartOffset();
        long end = descriptor.getLength();
        MediaPlayer mediaPlayer=new MediaPlayer();
        try {
            mediaPlayer.setDataSource(descriptor.getFileDescriptor(), start, end);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try {
            mediaPlayer.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }
        mediaPlayer.start();

*/



    }
    MediaPlayer.OnPreparedListener PreparedListener = new MediaPlayer.OnPreparedListener(){

        @Override
        public void onPrepared(MediaPlayer m) {
            try {
                if (m.isPlaying()) {
                    m.stop();
                    m.release();
                    m = new MediaPlayer();
                }
                m.start();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    };
    @Override
    protected void onResume() {
        super.onResume();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
    }
}

