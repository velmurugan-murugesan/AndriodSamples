package com.example.velm.threadhandlerexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    Thread thread1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        thread1 = new Thread(new ThreadClass());
        thread1.start();
    }

    public class ThreadClass extends Thread{

        Handler handler;

        @Override
        public void run() {
            handler = new Handler();
            handler.post(new Runnable() {
                @Override
                public void run() {

                }
            });
            Log.d("run ","called");
        }
    }
}
