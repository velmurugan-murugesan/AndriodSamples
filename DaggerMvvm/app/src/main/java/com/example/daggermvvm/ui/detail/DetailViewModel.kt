package com.example.daggermvvm.ui.detail

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.daggermvvm.data.model.Movie
import com.example.daggermvvm.repository.DetailRepositoryImpl
import com.example.daggermvvm.ui.home.MainViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.schedulers.Schedulers
import javax.inject.Inject

class DetailViewModel @Inject constructor(val detailRepositoryImpl: DetailRepositoryImpl) : ViewModel() {

    val TAG = DetailViewModel::class.java.name


    var compositeDisposable = CompositeDisposable()

    var movieList = MutableLiveData<List<Movie>>()

    fun fetchMovieDetails() {
        compositeDisposable.add(detailRepositoryImpl.getMovieDetails()
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