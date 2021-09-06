package com.example.daggermvvm.data.api

import com.example.daggermvvm.data.model.Movie
import io.reactivex.Single
import retrofit2.http.GET

interface ApiClient {

    @GET("movielist.json")
    fun getAllMovies() : Single<List<Movie>>

    @GET("volley_array.json")
    fun getDetails() : Single<List<Movie>>

}