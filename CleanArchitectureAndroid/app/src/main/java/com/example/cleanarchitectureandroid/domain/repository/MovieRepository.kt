package com.example.cleanarchitectureandroid.domain.repository

import com.example.cleanarchitectureandroid.data.Movie
import retrofit2.Response

interface MovieRepository  {
    suspend fun getAllMovies() : List<Movie>
}