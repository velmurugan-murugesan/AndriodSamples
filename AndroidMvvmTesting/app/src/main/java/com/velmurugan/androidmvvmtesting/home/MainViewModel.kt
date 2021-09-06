package com.velmurugan.androidmvvmtesting.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainViewModel @Inject constructor(private val mainRepositoryImpl: MainRepositoryImpl) : ViewModel() {

    val _movieList = MutableLiveData<List<Movie>>()
    val _errorMessage = MutableLiveData<String>()

    fun fetchAllMovies() {
        viewModelScope.launch {
            val response = mainRepositoryImpl.fetchAllMovies()
            if (response.isSuccessful) {
                _movieList.postValue(response.body())
            } else {
                _errorMessage.postValue(response.errorBody().toString())
            }

        }

    }

}