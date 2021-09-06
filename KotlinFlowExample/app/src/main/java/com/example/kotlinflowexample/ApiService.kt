package com.example.kotlinflowexample

import retrofit2.http.GET

interface ApiService {

    @GET("4ad9bb96-5905-4685-b2ea-c2a46790ce2c")
    suspend fun getAllMovies() : List<Movie>
}