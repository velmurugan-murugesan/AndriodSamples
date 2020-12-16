package com.example.kotlinhiltexample.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.example.kotlinhiltexample.R
import com.example.kotlinhiltexample.Status
import com.example.kotlinhiltexample.databinding.FragmentHomeBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val homeViewModel: HomeViewModel by viewModels()

    @Inject
    lateinit var movieAdapter: MovieAdapter
    lateinit var binding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.recyclerviewMovies.adapter = movieAdapter

        homeViewModel.allUsers.observe(viewLifecycleOwner, Observer {
            when (it.status) {

                Status.LOADING -> {

                }

                Status.SUCCESS -> {
                    it.data?.let { movies ->
                        movieAdapter.setMovies(movies)
                    }

                }

                Status.ERROR -> {

                }
            }
        })

        homeViewModel.getUserList()
    }

}