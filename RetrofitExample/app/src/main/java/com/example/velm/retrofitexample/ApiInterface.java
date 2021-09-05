package com.example.velm.retrofitexample;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by velmmuru on 8/19/2017.
 */

interface ApiInterface {

    @GET("movies/")
    Call<List<Movie>> getMovies();
}
