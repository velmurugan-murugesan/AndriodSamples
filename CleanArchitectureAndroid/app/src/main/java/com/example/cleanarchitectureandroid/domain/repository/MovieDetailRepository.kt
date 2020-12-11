package com.example.cleanarchitectureandroid.domain.repository

import com.example.cleanarchitectureandroid.data.MovieDetail

interface MovieDetailRepository {

    suspend fun getMovieDetail() : MovieDetail
}