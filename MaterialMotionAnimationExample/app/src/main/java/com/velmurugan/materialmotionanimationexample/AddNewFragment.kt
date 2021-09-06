package com.velmurugan.materialmotionanimationexample

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.velmurugan.materialmotionanimationexample.databinding.FragmentAddMovieBinding
import com.velmurugan.materialmotionanimationexample.databinding.HomeDetailFragmentBinding

class AddNewFragment : Fragment() {

    lateinit var addMovieBinding: FragmentAddMovieBinding
    private val args: HomeDetailFragmentArgs by navArgs()
    private val movie: Movie by lazy { args.movie }
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        addMovieBinding = FragmentAddMovieBinding.inflate(layoutInflater, container, false)
        return addMovieBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.fab)
            endView = addMovieBinding.containerAddNew
            duration = ENTER_ANIM_DURATION
            scrimColor = Color.TRANSPARENT
        }

        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = 220.toLong()
        }
    }
}