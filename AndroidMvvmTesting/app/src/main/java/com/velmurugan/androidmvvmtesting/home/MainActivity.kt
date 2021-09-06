package com.velmurugan.androidmvvmtesting.home

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.velmurugan.androidmvvmtesting.R
import com.velmurugan.androidmvvmtesting.databinding.ActivityMainBinding
import dagger.android.support.DaggerAppCompatActivity
import java.util.zip.Inflater
import javax.inject.Inject

class MainActivity : DaggerAppCompatActivity() {

    private val TAG = "MainActivity"
    lateinit var mainViewModel: MainViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var activityMainBinding: ActivityMainBinding



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val inflater = LayoutInflater.from(this)
        activityMainBinding = ActivityMainBinding.inflate(inflater)
        setContentView(activityMainBinding.root)

        mainViewModel = ViewModelProvider(this,viewModelFactory).get(MainViewModel::class.java)

        mainViewModel._movieList.observe(this, Observer {
            Log.d(TAG, "onCreate: $it")
        })

        mainViewModel.fetchAllMovies()
    }
}