package com.velmurugan.androidanimationexample.containertransform

import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialFadeThrough
import com.google.android.material.transition.MaterialSharedAxis
import com.velmurugan.androidanimationexample.ENTER_ANIM_DURATION
import com.velmurugan.androidanimationexample.R
import com.velmurugan.androidanimationexample.databinding.FragmentCreateNewBinding
import com.velmurugan.androidanimationexample.themeColor

class SearchViewFragment : Fragment() {
    lateinit var createNewBinding: FragmentCreateNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        createNewBinding = FragmentCreateNewBinding.inflate(layoutInflater, container, false)
        return createNewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough().apply {
            duration = ENTER_ANIM_DURATION
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}