package com.example.daggermvvm.ui.detail

import android.os.Bundle
import android.util.Log
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.example.daggermvvm.R
import com.example.daggermvvm.di.module.MyViewModelFactory
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    val TAG = DetailActivity::class.java.name

    lateinit var detailViewModel: DetailViewModel

    @Inject lateinit var viewModelFactory: MyViewModelFactory


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)

        detailViewModel.movieList.observe(this, Observer {

            it?.let {
                Log.d(TAG, it.toString())
            }
        })

        detailViewModel.fetchMovieDetails()

    }

}