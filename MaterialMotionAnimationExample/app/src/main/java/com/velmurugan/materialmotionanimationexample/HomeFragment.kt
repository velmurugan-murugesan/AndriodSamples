package com.velmurugan.materialmotionanimationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.transition.MaterialElevationScale
import com.velmurugan.materialmotionanimationexample.databinding.HomeFragmentBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeFragment : Fragment() {

    lateinit var homeBinding: HomeFragmentBinding
    lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeBinding = HomeFragmentBinding.inflate(layoutInflater, container, false)
        return homeBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        adapter = MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()

        homeBinding.rvMovies.adapter = adapter

        adapter.setMovieClickListener(object: ClickInterface<Movie>{
            override fun onClick(data: Movie, position: Int, view: View) {


                exitTransition = MaterialElevationScale(false).apply {
                    duration = ENTER_ANIM_DURATION
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = ENTER_ANIM_DURATION
                }

                val extras = FragmentNavigatorExtras(view to getString(R.string.email_card_detail_transition_name))
                val directions = HomeFragmentDirections.actionHomeFragmentToHomeDetailFragment(data)
                findNavController().navigate(directions, extras)
            }
        })

        homeBinding.fab.setOnClickListener {
            exitTransition = MaterialElevationScale(false).apply {
                duration = ENTER_ANIM_DURATION
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = ENTER_ANIM_DURATION
            }
            findNavController().navigate(R.id.action_homeFragment_to_addNewFragment)
        }

        GlobalScope.launch {
            val response = ApiService.getInstance().getAllMovies()
            withContext(Dispatchers.Main) {
                adapter.addMovieList(response)
                view.doOnPreDraw { startPostponedEnterTransition() }
            }


        }

    }

}