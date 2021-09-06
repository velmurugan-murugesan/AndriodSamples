package com.example.kotlinflowexample

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.InstallIn
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class MainViewModel @ViewModelInject constructor(private val movieRepository: MovieRepository): ViewModel() {

    val allUsers = MutableLiveData<Resource<List<Movie>>>()

    fun getUserList() {
        allUsers.value = Resource.loading(listOf())
        viewModelScope.launch {
            try {
                movieRepository.getAllMovies().collect {
                    allUsers.postValue(Resource.success(it))
                }
            } catch (e: Exception) {
                allUsers.postValue(Resource.error(listOf(), "Error"))
            }
        }
    }

}