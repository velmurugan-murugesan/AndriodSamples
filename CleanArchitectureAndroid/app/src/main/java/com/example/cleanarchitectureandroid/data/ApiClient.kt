package com.example.cleanarchitectureandroid.data

import retrofit2.http.GET

interface ApiClient {

    @GET("4ad9bb96-5905-4685-b2ea-c2a46790ce2c")
    suspend fun getAllMovies() : List<Movie>

    @GET("49ad852e-a129-445e-bdc0-dbd5b7681862")
    suspend fun getMovieDetail() : MovieDetail

}