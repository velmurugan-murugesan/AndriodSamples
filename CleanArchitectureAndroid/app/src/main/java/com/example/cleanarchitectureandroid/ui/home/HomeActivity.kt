package com.example.cleanarchitectureandroid.ui.home

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.GeneratedAdapter
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.data.Resource
import com.example.cleanarchitectureandroid.data.Status
import com.example.cleanarchitectureandroid.databinding.ActivityHomeBinding
import com.example.cleanarchitectureandroid.di.module.MyViewModelFactory
import com.example.cleanarchitectureandroid.ui.detail.DetailActivity
import com.example.cleanarchitectureandroid.utils.ClickListener
import com.example.cleanarchitectureandroid.utils.ResultWrapper
import dagger.android.support.DaggerAppCompatActivity
import kotlinx.android.synthetic.main.activity_home.*
import javax.inject.Inject

class HomeActivity : DaggerAppCompatActivity() {
    lateinit var homeViewModel: HomeViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory
    lateinit var movieAdapter: MovieAdapter
    lateinit var binding: ActivityHomeBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        binding.lifecycleOwner = this
        movieAdapter = MovieAdapter()
        binding.recyclerviewMovies.adapter = movieAdapter
        homeViewModel = ViewModelProviders.of(this, viewModelFactory).get(HomeViewModel::class.java)
        movieAdapter.setMovieClickListener(movieItemClickListener)
        homeViewModel.getAllMovies().observe(this, ::allMovieResponse)
        homeViewModel.fetchAllMovies()
    }

    private val movieItemClickListener = object : ClickListener<Movie> {
        override fun onCLick(data: Movie) {
            startActivity(Intent(this@HomeActivity, DetailActivity::class.java))
        }
    }

    private fun allMovieResponse(resource: Resource<List<Movie>>) {
        when (resource.status) {
            Status.SUCCESS -> {
                resource.data?.let {
                    movieAdapter.setMovies(it)
                }
            }
            Status.ERROR -> {
                Toast.makeText(this, resource.message, Toast.LENGTH_SHORT).show()
            }
            else -> {
            }
        }
    }
}