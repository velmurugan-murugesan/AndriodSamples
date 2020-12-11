package com.example.cleanarchitectureandroid.domain.usecases

import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.MovieRepositoryImpl
import javax.inject.Inject

class GetAllMoviesUseCase @Inject constructor(private val movieRepositoryImpl: MovieRepositoryImpl) : SingleUseCase<List<Movie>> {

    override suspend fun execute() = movieRepositoryImpl.getAllMovies()

}