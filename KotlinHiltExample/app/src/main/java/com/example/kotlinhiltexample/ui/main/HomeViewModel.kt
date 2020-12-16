package com.example.kotlinhiltexample.ui.main

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.kotlinhiltexample.Movie
import com.example.kotlinhiltexample.MovieRepository
import com.example.kotlinhiltexample.Resource
import kotlinx.coroutines.launch

class HomeViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository) : ViewModel() {

    val allUsers = MutableLiveData<Resource<List<Movie>>>()

    fun getUserList() {
        allUsers.value = Resource.loading(listOf())
        viewModelScope.launch {
            try {
                allUsers.postValue(Resource.success(movieRepository.getAllMovies()))
            } catch (e: Exception) {
                allUsers.postValue(Resource.error(listOf(), "Error"))
            }
        }
    }
}