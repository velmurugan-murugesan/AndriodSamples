package com.example.daggermvvm.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.daggermvvm.R
import dagger.android.support.DaggerFragment
import kotlinx.android.synthetic.main.fragment_home.*
import javax.inject.Inject

class OneFragment : DaggerFragment() {

    companion object {

        val TAG = OneFragment::class.java.name
    }

    lateinit var oneViewModel: OneViewModel
    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        oneViewModel = ViewModelProviders.of(this, viewModelFactory).get(OneViewModel::class.java)

        oneViewModel.movieList.observe(this, Observer {

            it?.let {
                Log.d(TAG, it.toString())
            }
        })

        oneViewModel.fetchAllMovies()

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        textview.setOnClickListener {


        }
    }

}