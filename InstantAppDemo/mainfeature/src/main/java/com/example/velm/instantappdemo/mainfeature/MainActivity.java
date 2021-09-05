package com.example.velm.instantappdemo.mainfeature;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    RecyclerView recyclerView;
    RecyclerviewAdapter recyclerviewAdapter;

    List<Movie> movieList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       /* Intent i = new Intent(Intent.ACTION_VIEW, Uri.parse("https://velm.example.com/feature2"));
        i.setPackage(getPackageName());
        i.addCategory(Intent.CATEGORY_APP_BROWSER);
        i.putExtra("name","velmur");
        startActivity(i);*/

       movieList = new ArrayList<>();

       Movie movie1 = new Movie("Titanic","1997",R.drawable.ic_launcher_background,"James");
        Movie movie2 = new Movie("Titanic 2","1998",R.drawable.ic_launcher_background,"James");

        Movie movie3 = new Movie("Spiderman 2","1998",R.drawable.ic_launcher_background,"James");

        movieList.add(movie1);
        movieList.add(movie2);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);

        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerviewAdapter = new RecyclerviewAdapter(getApplicationContext(),movieList);

        recyclerView.setAdapter(recyclerviewAdapter);




    }
}
