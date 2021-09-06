package com.velmurugan.androidmvvmtesting.home

import com.velmurugan.androidmvvmtesting.ApiInterface
import retrofit2.Response
import javax.inject.Inject

class MainRepositoryImpl @Inject constructor(private val apiInterface: ApiInterface) : MainRepository {

    override suspend fun fetchAllMovies(): Response<List<Movie>> {
        return apiInterface.getAllMovies()
    }
}