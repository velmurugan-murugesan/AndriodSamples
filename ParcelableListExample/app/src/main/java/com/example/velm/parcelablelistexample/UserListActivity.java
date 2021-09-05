package com.example.velm.parcelablelistexample;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Toast;

import java.util.List;

/**
 * Created by velmmuru on 9/21/2017.
 */

public class UserListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.userlist_activity);


        Intent userListIntent = getIntent();

        List<User> userList = userListIntent.getParcelableArrayListExtra("userlist");

        Log.d("UserList = ",userList.toString());

        Toast.makeText(getApplicationContext(),userList.toString(),Toast.LENGTH_SHORT).show();


    }
}
