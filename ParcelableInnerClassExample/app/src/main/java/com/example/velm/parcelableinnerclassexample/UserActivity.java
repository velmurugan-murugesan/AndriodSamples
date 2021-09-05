package com.example.velm.parcelableinnerclassexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

/**
 * Created by velmmuru on 9/21/2017.
 */

public class UserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_activity);

        Intent userIntent = getIntent();

        User user = userIntent.getParcelableExtra("user");

        Log.d("User = ",user.toString());

        Toast.makeText(getApplicationContext(),user.toString(),Toast.LENGTH_SHORT).show();

    }
}
