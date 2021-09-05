package com.example.velm.startedservice;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void startService(View view){

        if(!MyService.isMyServiceRunning()){
            Intent startIntent = new Intent(this,MyService.class);
            startService(startIntent);
        } else {
            Log.d("Main","Service in running");
        }
    }

    public void stopService(View view){

        if(MyService.isMyServiceRunning()){
            Intent stopIntent = new Intent(this,MyService.class);
            stopService(stopIntent);
        } else {
            Log.d("Main","Service not running");
        }


    }

}
