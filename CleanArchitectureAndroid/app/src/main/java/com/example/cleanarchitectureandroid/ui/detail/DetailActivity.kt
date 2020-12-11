package com.example.cleanarchitectureandroid.ui.detail

import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.data.Status
import com.example.cleanarchitectureandroid.di.module.ViewModelModule
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_detail.*
import javax.inject.Inject

class DetailActivity : DaggerAppCompatActivity() {

    @Inject lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var detailViewModel: DetailViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)


        detailViewModel = ViewModelProviders.of(this, viewModelFactory).get(DetailViewModel::class.java)


        detailViewModel.fetchMovieDetail()

            detailViewModel.getMovieDetails().observe(this, Observer {

            it?.let {

                when(it.status) {

                    Status.SUCCESS -> {
                        val movieDetail = it.data
                        textTitle.text = movieDetail!!.title
                        textDesc.text = movieDetail!!.desc
                        textDate.text = movieDetail!!.date
                        Glide.with(this).load(movieDetail!!.image).into(poster)
                    }
                    else -> {

                    }
                }


            }

        })

    }

}