package com.example.velm.receivetext;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

/**
 * Created by velmmuru on 9/21/2017.
 */

public class ReceiverActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent intent = getIntent();

        /*if(intent.getAction().equals(Intent.ACTION_VIEW)){
            Uri message = intent.getData();

            Toast.makeText(getApplicationContext(),message.toString(),Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(getApplicationContext(),"Normal call",Toast.LENGTH_SHORT).show();

        }*/

        if(intent.getAction().equals(Intent.ACTION_SEND)){

            String message = intent.getExtras().getString(Intent.EXTRA_TEXT);
            Toast.makeText(getApplicationContext(),message,Toast.LENGTH_SHORT).show();

        } else {
            Toast.makeText(getApplicationContext(),"Activity call",Toast.LENGTH_SHORT).show();

        }


    }
}
