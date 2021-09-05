package com.example.velm.parcelablelistexample;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }


    public void sendIntent(View view){
        List<User> userList = new ArrayList<>();
        User user1 = new User("User 1",22,"Chennai");
        userList.add(user1);
        User user2 = new User("User 2",25,"Banglore");
        userList.add(user2);

        Intent userListIntent = new Intent(getApplicationContext(),UserListActivity.class);
        userListIntent.putParcelableArrayListExtra("userlist",(ArrayList<? extends Parcelable>) userList);
        startActivity(userListIntent);
    }
}
