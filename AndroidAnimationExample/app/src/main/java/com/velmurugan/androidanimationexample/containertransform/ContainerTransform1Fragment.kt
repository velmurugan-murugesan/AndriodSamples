package com.velmurugan.androidanimationexample.containertransform

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.doOnPreDraw
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.google.android.material.transition.MaterialContainerTransform
import com.velmurugan.androidanimationexample.ENTER_ANIM_DURATION
import com.velmurugan.androidanimationexample.Movie
import com.velmurugan.androidanimationexample.R
import com.velmurugan.androidanimationexample.databinding.FragmentContainerTransform1Binding
import com.velmurugan.androidanimationexample.themeColor

class ContainerTransform1Fragment : Fragment() {

    lateinit var containerTransform1Binding: FragmentContainerTransform1Binding
    private val args: ContainerTransform1FragmentArgs by navArgs()
    private val movie: Movie by lazy { args.movie }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        containerTransform1Binding =
            FragmentContainerTransform1Binding.inflate(layoutInflater, container, false)
        return containerTransform1Binding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        sharedElementEnterTransition = MaterialContainerTransform().apply {
            // Scope the transition to a view in the hierarchy so we know it will be added under
            // the bottom app bar but over the elevation scale of the exiting HomeFragment.
            drawingViewId = R.id.navController
            duration = ENTER_ANIM_DURATION
            scrimColor = Color.TRANSPARENT
            setAllContainerColors(requireContext().themeColor(R.attr.colorSurface))

        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        Glide.with(requireContext()).load(movie.imageUrl)
            .apply(
                RequestOptions()
            )
            .centerCrop()
            .into(containerTransform1Binding.imgMovie)
        containerTransform1Binding.textTitle.text = movie.name
        containerTransform1Binding.textTag.text = movie.category
        containerTransform1Binding.textDesc.text = movie.desc

    }
}