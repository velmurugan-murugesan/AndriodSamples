package com.example.velm.parcelableinnerclassexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void sendIntent(View view){

        Address address = new Address(3,"2nd street","Chennai");
        User user = new User("user 1",22,address);
        Intent userIntent = new Intent(getApplicationContext(),UserActivity.class);
        userIntent.putExtra("user",user);
        startActivity(userIntent);

    }
}
