package com.velmurugan.androidanimationexample.containertransform

import android.graphics.Color
import android.os.Bundle
import android.transition.Slide
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialContainerTransform
import com.google.android.material.transition.MaterialSharedAxis
import com.velmurugan.androidanimationexample.ENTER_ANIM_DURATION
import com.velmurugan.androidanimationexample.R
import com.velmurugan.androidanimationexample.databinding.FragmentCreateNewBinding
import com.velmurugan.androidanimationexample.themeColor

class CreateNewFragment : Fragment() {
    lateinit var createNewBinding: FragmentCreateNewBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        createNewBinding = FragmentCreateNewBinding.inflate(layoutInflater, container, false)
        return createNewBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        enterTransition = MaterialContainerTransform().apply {
            startView = requireActivity().findViewById(R.id.fabCreateMovie)
            endView = createNewBinding.layoutG
            duration = ENTER_ANIM_DURATION
            scrimColor = Color.TRANSPARENT
            containerColor = requireContext().themeColor(R.attr.colorSurface)
            startContainerColor = requireContext().themeColor(R.attr.colorSecondary)
            endContainerColor = requireContext().themeColor(R.attr.colorSurface)
        }

        exitTransition = MaterialSharedAxis(MaterialSharedAxis.Z, true).apply {
            duration = 220.toLong()
        }
    }
}