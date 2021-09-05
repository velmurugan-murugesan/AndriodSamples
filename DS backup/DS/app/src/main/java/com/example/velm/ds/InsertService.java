package com.example.velm.ds;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import static android.content.ContentValues.TAG;

/**
 * Created by velmmuru on 9/17/2017.
 */

public class InsertService extends Service {

    public Runnable mRunnable = null;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {


        BufferedReader reader = null;
        try {
            reader = new BufferedReader(new InputStreamReader(getApplicationContext().getAssets().open("words.txt")));
            StringBuilder sb = new StringBuilder();
            String mLine = reader.readLine();
            while (mLine != null) {
                sb.append(mLine); // process line
                mLine = reader.readLine();
                if(mLine != null){
                    Log.d(TAG,mLine);
                }

            }
            reader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }

        // do reading, usually loop until end of file reading


        /*final Handler mHandler = new Handler();
        mRunnable = new Runnable() {
            @Override
            public void run() {
                MyDBHelper myDBHelper = new MyDBHelper(getApplicationContext());
                boolean isInfoAvailable = myDBHelper.isAnyInfoAvailable(getApplicationContext());
                Toast.makeText(getApplicationContext(), String.valueOf(isInfoAvailable), Toast.LENGTH_LONG).show();
                mHandler.postDelayed(mRunnable, 10 * 1000);
            }
        };
        mHandler.postDelayed(mRunnable, 10 * 1000);*/

        stopSelf();
        return START_NOT_STICKY;
    }

    @Override
    public void onDestroy() {
        Log.d("OnDestroy","DEs");
        super.onDestroy();
    }
}
