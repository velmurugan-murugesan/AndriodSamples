package com.example.cleanarchitectureandroid.data

import com.example.cleanarchitectureandroid.domain.repository.MovieRepository
import javax.inject.Inject

class MovieRepositoryImpl @Inject constructor(val apiClient: ApiClient) : MovieRepository {
    override suspend fun getAllMovies() = apiClient.getAllMovies()
}