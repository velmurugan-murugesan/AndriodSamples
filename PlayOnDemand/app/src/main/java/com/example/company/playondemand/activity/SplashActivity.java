package com.example.company.playondemand.activity;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.company.playondemand.R;
import com.example.company.playondemand.utils.Singleton;
import com.example.company.playondemand.utils.Utils;

import java.io.File;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by velmmuru on 9/9/2016.
 */
public class SplashActivity extends AppCompatActivity {

    String TAG ="SplashActivity";
    private TextView tvGymName;
    private Context context;
    Timer timer;
    private static final int PICKFILE_RESULT_CODE = 1;
    Utils utils = null;
    String filePath = null;
    private static final int REQUEST_WRITE_STORAGE = 112;
    AlertDialog dialog = null;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_layout);
        context = this;
        initView();
    }

    private void initView() {
        Log.d(TAG,"initview called");
        tvGymName = (TextView) findViewById(R.id.tvGymName);
        utils = Singleton.getUtilInstance();
        tvGymName.setTypeface(utils.getTypeface(context));
        checkPermission();
    }

    private void checkPermission(){
        Log.d(TAG,"check permission called");
        int permission = ContextCompat.checkSelfPermission(this,
                Manifest.permission.WRITE_EXTERNAL_STORAGE);
        if(permission != PackageManager.PERMISSION_GRANTED){
            Log.d(TAG,"ask permission called");
            askPermission();
        } else {
            Log.d(TAG,"starttimer called from  checkPermission called");
            startTimer();
        }
    }

    protected void makeRequest() {
        ActivityCompat.requestPermissions(this,
                new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},
                REQUEST_WRITE_STORAGE);
    }

    protected void startTimer() {
        if (timer != null) {
            timer.cancel();
        }
        timer = new Timer();
        MyTimerTask myTimerTask = new MyTimerTask();
        timer.schedule(myTimerTask, 5000);
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG,"onResume called");
        //checkPermission();
        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
        /*if(filePath == null){
            startTimer();
            Toast.makeText(context, "OnResume", Toast.LENGTH_LONG).show();

        }*/
    }




    class MyTimerTask extends TimerTask {

        @Override
        public void run() {
            String filePath = utils.getSharedFilePath(context);
            if(filePath != null && filePath.length() > 0){
                Log.d(TAG,"start acitvity called from timer");
                startActivity();
            } else {
                Log.d(TAG,"startactivityFor filr called from timer");
                startActivityForFile();
            }
            /*Intent mainIntent = new Intent(context, MainActivity.class);
            startActivity(mainIntent);
            finish();*/
            //String filePath =

            //ask for the permission in android M




        }
    }

    private void startActivityForFile(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        //intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        intent.setType("file*//*");
        startActivityForResult(intent,PICKFILE_RESULT_CODE);
    }

    private void startActivity(){
        Intent mainIntent = new Intent(context, MainActivity.class);
        startActivity(mainIntent);
        finish();
    }


    private void askPermission(){

        if (ActivityCompat.shouldShowRequestPermissionRationale(this,
                    Manifest.permission.WRITE_EXTERNAL_STORAGE)) {
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage("Permission to access the SD-CARD is required for this app to Download PDF.")
                        .setTitle("Permission required");

                builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {

                    public void onClick(DialogInterface dialog, int id) {
                        makeRequest();
                    }
                });

                dialog = builder.create();
                dialog.show();

            } else {
                makeRequest();
            }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case REQUEST_WRITE_STORAGE: {
                if(dialog != null && dialog.isShowing()){
                    dialog.dismiss();
                }
                if (grantResults.length == 0
                        || grantResults[0] !=
                        PackageManager.PERMISSION_GRANTED) {
                        //askPermission();
                    finish();
                    Log.d(TAG, "Finished called");
                    Log.i("Splash", "Permission has been denied by user");

                } else {
                    Log.d(TAG, "Finished startActivityForFile called");
                    startActivityForFile();
                    Log.i("Splash", "Permission has been granted by user");

                }
                return;
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        switch (requestCode) {
            case PICKFILE_RESULT_CODE:
                if (resultCode == RESULT_OK) {
                    Log.d(TAG,"Inside on activity result");
                    String filePath = data.getData().getPath();
                    Log.d("Splash", "File path = " + filePath);
                    File file = new File(filePath);
                    Log.d("file.getPath = ",file.getPath());
                    Log.d("file.getParent()", file.getParent());
                    Log.d("file.getName()",file.getName());
                    //.makeText(context, "file"+file.getAbsolutePath(), Toast.LENGTH_SHORT).show();
                    //Toast.makeText(context,"Parent"+file.getParent(),Toast.LENGTH_SHORT).show();
                    utils.setSharedFilePath(context, filePath);
                    utils.setSharedParentPath(context, file.getParent());
                    Log.d(TAG,"start activity called from activity result");
                    startActivity();
                }
                break;
        }
    }
}