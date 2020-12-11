package com.example.cleanarchitectureandroid.data

import com.example.cleanarchitectureandroid.domain.repository.MovieDetailRepository
import javax.inject.Inject

class MovieDetailRepositoryImpl @Inject constructor(private val apiClient: ApiClient) : MovieDetailRepository {
    override suspend fun getMovieDetail() = apiClient.getMovieDetail()
}