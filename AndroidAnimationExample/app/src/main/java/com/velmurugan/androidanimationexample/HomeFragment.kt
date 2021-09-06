package com.velmurugan.androidanimationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.velmurugan.androidanimationexample.databinding.FragmentHomeBinding

class HomeFragment : Fragment() {
    lateinit var homeFragmentHomeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        homeFragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater, container, false)
        return homeFragmentHomeBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        homeFragmentHomeBinding.apply {

            viewContainerTransformation.setOnClickListener {
                findNavController().navigate(R.id.action_homeFragment_to_containerTransformFragment)
            }
        }

    }
}