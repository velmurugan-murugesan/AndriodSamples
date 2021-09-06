package com.example.daggermvvm.repository

import com.example.daggermvvm.data.model.Movie
import io.reactivex.Single

interface DetailRepository {

    fun getMovieDetails() : Single<List<Movie>>

}