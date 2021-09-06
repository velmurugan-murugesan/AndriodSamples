package com.example.daggermvvm.repository

import com.example.daggermvvm.data.model.Movie
import io.reactivex.Single

interface MovieRepository {

    fun getAllMovies() : Single<List<Movie>>
}