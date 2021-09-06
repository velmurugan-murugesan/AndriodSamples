package com.velmurugan.androidmvvmtesting.home

import retrofit2.Response

interface MainRepository {
    suspend fun fetchAllMovies() : Response<List<Movie>>
}