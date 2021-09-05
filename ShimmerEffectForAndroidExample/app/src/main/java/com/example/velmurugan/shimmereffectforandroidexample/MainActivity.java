package com.example.velmurugan.shimmereffectforandroidexample;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import com.facebook.shimmer.ShimmerFrameLayout;
import java.util.ArrayList;
import java.util.List;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

  private ShimmerFrameLayout shimmerFrameLayout;
  private RecyclerView recyclerView;
  private RecyclerviewAdapter recyclerviewAdapter;
  private List<Movie> movieList;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_main);

    shimmerFrameLayout = (ShimmerFrameLayout) findViewById(R.id.shimmerLayout);
    shimmerFrameLayout.startShimmerAnimation();

    movieList = new ArrayList<>();
    recyclerviewAdapter = new RecyclerviewAdapter(this);
    recyclerView = (RecyclerView) findViewById(R.id.recyclerview);
    recyclerView.setLayoutManager(new LinearLayoutManager(this));
    recyclerView.setItemAnimator(new DefaultItemAnimator());
    recyclerView.setAdapter(recyclerviewAdapter);

    new Handler().postDelayed(new Runnable() {
      @Override
      public void run() {
        loadData();
      }
    }, 5000);
  }

  private void loadData(){
    ApiInterface apiInterface = ApiClient.getClient().create(ApiInterface.class);

    apiInterface.getAllUsers().enqueue(new Callback<List<Movie>>() {
      @Override
      public void onResponse(retrofit2.Call<List<Movie>> call, Response<List<Movie>> response) {

        movieList = response.body();
        recyclerviewAdapter.setMovieList(movieList);
        shimmerFrameLayout.stopShimmerAnimation();
        shimmerFrameLayout.setVisibility(View.GONE);
      }

      @Override
      public void onFailure(retrofit2.Call<List<Movie>> call, Throwable t) {

      }
    });
  }
}
