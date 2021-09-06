package com.example.daggermvvm.ui.fragments

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.model.Movie
import com.example.daggermvvm.repository.MovieRepositoryImpl
import com.example.daggermvvm.ui.home.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class OneViewModel @Inject constructor(val movieRepositoryImpl: MovieRepositoryImpl) : ViewModel() {


    val TAG = OneViewModel::class.java.name


    var compositeDisposable = CompositeDisposable()

    var movieList = MutableLiveData<List<Movie>>()

    fun fetchAllMovies() {
        compositeDisposable.add(movieRepositoryImpl.getAllMovies()
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe({

                it?.let {
                    Log.d(TAG, it.toString())
                    movieList.postValue(it)
                }

            }, {
                it?.let {
                    Log.d(TAG, it.message ?: "No Message")
                }
            }))

    }

    override fun onCleared() {
        super.onCleared()
        compositeDisposable.dispose()
    }





}