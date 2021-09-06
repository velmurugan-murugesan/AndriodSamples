package com.velmurugan.androidmvvmtesting

import com.velmurugan.androidmvvmtesting.home.Movie
import retrofit2.Response
import retrofit2.http.GET

interface ApiInterface {
    @GET("movielist.json")
    suspend fun getAllMovies(): Response<List<Movie>>
}
