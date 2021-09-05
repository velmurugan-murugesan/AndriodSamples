package com.example.velm.bindservice;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;

/**
 * Created by velmmuru on 9/13/2017.
 */

public class MyService extends Service {


    String TAG ="MyService";
    @Override
    public void onCreate() {
        super.onCreate();
        Log.d(TAG,"onCreate");
    }

    Binder binder = new Binder();

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return binder;
    }


    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.d(TAG,"onDestroy");
    }

    class MyBinder extends Binder{

        MyService getService(){
            return MyService.this;
        }

    }
}
