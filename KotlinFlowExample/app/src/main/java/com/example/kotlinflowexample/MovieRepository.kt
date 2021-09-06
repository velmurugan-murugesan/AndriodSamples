package com.example.kotlinflowexample

import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class MovieRepository @Inject constructor(private val apiService: ApiService) {
    suspend fun getAllMovies() = flow {
      val data = apiService.getAllMovies()
      emit(data)
        kotlinx.coroutines.delay(1000)
        emit(data)
    }

}