package com.example.velm.parcelableexample;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        User user = new User(1,"velm",25);
        Intent i = new Intent(getApplicationContext(),UserActvity.class);
        i.putExtra("user",user);
        startActivity(i);


    }
}
