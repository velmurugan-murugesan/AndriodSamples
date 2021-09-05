package com.example.velm.retrofitexample;

import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {


    private List<Movie> movieList;
    private RecyclerView recyclerView;

    private RecyclerAdapter recyclerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        movieList = new ArrayList<>();

        recyclerView = (RecyclerView)findViewById(R.id.recyclerview);


        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);


        ApiInterface apiService = ApiClient.getClient().create(ApiInterface.class);

        Call<List<Movie>> call = apiService.getMovies();

        call.enqueue(new Callback<List<Movie>>() {
            @Override
            public void onResponse(@NonNull Call<List<Movie>> call, @NonNull Response<List<Movie>> response) {

                movieList = response.body();
                Log.d("TAG","Response = "+movieList);
                recyclerAdapter = new RecyclerAdapter(getApplicationContext(),movieList);
                recyclerView.setAdapter(recyclerAdapter);

            }

            @Override
            public void onFailure(@NonNull Call<List<Movie>> call2, @NonNull Throwable t) {
                Log.d("TAG","Failed Response = "+t.toString());

                //call2.cancel();
                Log.d("TAG","canceled");
            }
        });

    }

    /*@Override
    protected void onDestroy() {
        super.onDestroy();
        Log.d("TAG","Ondestroy");
        if(call.isExecuted()){
            call.cancel();
            Log.d("TAG","call canedlled");
        } else {
            Log.d("TAG","NO calls");
        }
    }*/
}
