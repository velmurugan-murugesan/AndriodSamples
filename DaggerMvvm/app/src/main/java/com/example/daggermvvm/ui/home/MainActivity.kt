package com.example.daggermvvm.ui.home

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.FragmentTransaction
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.daggermvvm.R
import com.example.daggermvvm.ui.fragments.OneFragment
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject


class MainActivity : DaggerAppCompatActivity() {

    val TAG = "MainActivity"

    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mainViewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)


        mainViewModel.movieList.observe(this, Observer {
            Log.d(TAG, it.toString())
            //startActivity(Intent(this, DetailActivity::class.java))
            val transaction: FragmentTransaction = supportFragmentManager.beginTransaction()
            transaction.replace(R.id.container, OneFragment(), OneFragment.TAG)
            transaction.addToBackStack(null)
            transaction.commit()
        })

        mainViewModel.fetchAllMovies()


    }
}