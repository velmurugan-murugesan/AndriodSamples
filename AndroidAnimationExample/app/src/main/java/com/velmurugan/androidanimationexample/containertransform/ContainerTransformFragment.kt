package com.velmurugan.androidanimationexample.containertransform

import android.os.Bundle
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import com.google.android.material.card.MaterialCardView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.dialog.MaterialDialogs
import com.google.android.material.transition.MaterialElevationScale
import com.google.android.material.transition.MaterialFade
import com.google.android.material.transition.MaterialFadeThrough
import com.velmurugan.androidanimationexample.ApiService
import com.velmurugan.androidanimationexample.ENTER_ANIM_DURATION
import com.velmurugan.androidanimationexample.Movie
import com.velmurugan.androidanimationexample.R
import com.velmurugan.androidanimationexample.databinding.FragmentContainerTransformBinding
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ContainerTransformFragment : Fragment() {

    /*A card into a details page
    A list item into a details page
    A FAB into a details page
    A search bar into expanded search*/

    lateinit var adapter: ContainerTransformAdapter

    lateinit var containerTransformBinding: FragmentContainerTransformBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        containerTransformBinding =
            FragmentContainerTransformBinding.inflate(layoutInflater, container, false)
        return containerTransformBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        postponeEnterTransition()
        adapter = ContainerTransformAdapter()
        adapter.setMovieClickListener(object : ClickInterface<Movie> {


            override fun onClick(data: Movie, position: Int, viewsa: View) {

                exitTransition = MaterialElevationScale(false).apply {
                    duration = ENTER_ANIM_DURATION
                }
                reenterTransition = MaterialElevationScale(true).apply {
                    duration = ENTER_ANIM_DURATION
                }

                val extras = FragmentNavigatorExtras(viewsa to getString(R.string.email_card_detail_transition_name))
                 val directions = ContainerTransformFragmentDirections.actionContainerTransformFragmentToContainerTransform1Fragment(data)
                findNavController().navigate(directions, extras)
            }
        })

        containerTransformBinding.fabCreateMovie.setOnClickListener {
            exitTransition = MaterialElevationScale(false).apply {
                duration = ENTER_ANIM_DURATION
            }
            reenterTransition = MaterialElevationScale(true).apply {
                duration = ENTER_ANIM_DURATION
            }
            findNavController().navigate(R.id.action_containerTransformFragment_to_createNewFragment)
        }

        containerTransformBinding.searchView.setOnClickListener {

            exitTransition = MaterialFadeThrough().apply {
                duration = ENTER_ANIM_DURATION
            }
            findNavController().navigate(R.id.action_containerTransformFragment_to_searchViewFragment)

        }

        GlobalScope.launch {
           val response = ApiService.getInstance().getAllMovies()
            withContext(Dispatchers.Main) {
                containerTransformBinding.rvMovies.adapter = adapter
                adapter.addMovieList(response)
                view.doOnPreDraw { startPostponedEnterTransition() }
            }
        }

    }
}