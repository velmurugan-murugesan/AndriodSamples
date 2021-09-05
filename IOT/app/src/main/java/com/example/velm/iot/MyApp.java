package com.example.velm.iot;

import android.app.Application;
import android.os.StrictMode;

import com.squareup.leakcanary.LeakCanary;

/**
 * Created by velmmuru on 10/4/2017.
 */

public class MyApp extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        setupLeakCanary();
    }

    private void setupLeakCanary() {
        if(LeakCanary.isInAnalyzerProcess(this)){
            return;
        } else {
            enabledStrictMode();
            LeakCanary.install(this);
        }
    }

    private void enabledStrictMode() {
        StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder().build());
    }
}
