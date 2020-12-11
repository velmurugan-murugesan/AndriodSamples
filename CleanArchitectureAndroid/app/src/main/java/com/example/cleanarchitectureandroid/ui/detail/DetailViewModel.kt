package com.example.cleanarchitectureandroid.ui.detail

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import androidx.lifecycle.viewModelScope
import com.example.cleanarchitectureandroid.data.MovieDetail
import com.example.cleanarchitectureandroid.data.Resource
import com.example.cleanarchitectureandroid.domain.usecases.MovieDetailUseCase
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class DetailViewModel @Inject constructor(private val movieDetailsUseCase: MovieDetailUseCase) : ViewModel() {


    private val movieDetail = MutableLiveData<Resource<MovieDetail>>()


    fun fetchMovieDetail() {

        viewModelScope.launch {

            movieDetail.postValue(Resource.loading(data = null))

            try {
                val response = movieDetailsUseCase.execute()
                movieDetail.postValue(Resource.success(response))
            }
            catch (e: Exception) {
                movieDetail.postValue(Resource.error(data = null, message = "Error"))
            }


        }

    }

    fun getMovieDetails() = movieDetail




}