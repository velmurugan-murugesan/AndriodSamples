package com.velmurugan.materialmotionanimationexample

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.transition.MaterialContainerTransform
import com.velmurugan.materialmotionanimationexample.databinding.HomeDetailFragmentBinding
import com.velmurugan.materialmotionanimationexample.databinding.HomeFragmentBinding

class HomeDetailFragment : Fragment() {

    lateinit var homeDetailBinding: HomeDetailFragmentBinding
    private val args: HomeDetailFragmentArgs by navArgs()
    private val movie: Movie by lazy { args.movie }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        homeDetailBinding = HomeDetailFragmentBinding.inflate(layoutInflater, container, false)
        return homeDetailBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.navController
            duration = ENTER_ANIM_DURATION
            scrimColor = Color.TRANSPARENT

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Glide.with(requireContext()).load(movie.imageUrl)
            .apply(
                RequestOptions()
            )
            .centerCrop()
            .into(homeDetailBinding.imgMovie)
        homeDetailBinding.textTitle.text = movie.name
        homeDetailBinding.textTag.text = movie.category
        homeDetailBinding.textDesc.text = movie.desc
    }

}