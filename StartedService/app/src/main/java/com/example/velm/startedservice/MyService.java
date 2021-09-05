package com.example.velm.startedservice;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by velmmuru on 9/13/2017.
 */

public class MyService extends Service {

    String TAG="MyService";

    MediaPlayer myPlayer;

    private static MyService instance = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    public static boolean isMyServiceRunning(){
        return instance != null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
        instance = this;
        myPlayer = MediaPlayer.create(this, R.raw.kalimba);
        myPlayer.setLooping(false); // Set looping
    }


    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.d(TAG,"onStartCommand");
        //myPlayer.start();
        for (int i = 0; i < 100; i++){
            Log.d(TAG,i+"");
        }
        return START_STICKY;

    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        instance = null;
        Log.d(TAG,"onDestroy");
        myPlayer.stop();
    }
}
