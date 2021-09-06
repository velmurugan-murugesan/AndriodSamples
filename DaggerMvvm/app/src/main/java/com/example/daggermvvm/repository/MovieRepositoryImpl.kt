package com.example.daggermvvm.repository

import com.example.daggermvvm.data.api.ApiClient
import com.example.daggermvvm.data.model.Movie
import io.reactivex.Single
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiClient: ApiClient) : MovieRepository {
    override fun getAllMovies(): Single<List<Movie>> {
        return apiClient.getAllMovies()
    }


}