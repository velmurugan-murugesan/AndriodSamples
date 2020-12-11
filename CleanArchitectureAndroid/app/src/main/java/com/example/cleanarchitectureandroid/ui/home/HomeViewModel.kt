package com.example.cleanarchitectureandroid.ui.home

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.Resource
import com.example.cleanarchitectureandroid.domain.usecases.GetAllMoviesUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class HomeViewModel @Inject constructor(private val getAllMoviesUseCase: GetAllMoviesUseCase) : ViewModel() {


    private val movieList = MutableLiveData<Resource<List<Movie>>>()


    fun fetchAllMovies() {

        viewModelScope.launch {
            movieList.postValue(Resource.loading(data = null))
            try {
                val response = getAllMoviesUseCase.execute()
                movieList.postValue(Resource.success(response))
            }
            catch (e: Exception) {
                movieList.postValue(Resource.error(data = null,message = "Error"))
            }

        }
    }

    fun getAllMovies() = movieList



}