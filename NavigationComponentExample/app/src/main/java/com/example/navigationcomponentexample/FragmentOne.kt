package com.example.navigationcomponentexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_1.*

class FragmentOne : Fragment() {


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_1, container, false)

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        buttonFragment1.setOnClickListener {
            val action = FragmentOneDirections.actionFragmentOneToFragmentTwo("velmurugan")
            it.findNavController().navigate(action)
        }

        buttonFragment1To4.setOnClickListener {
            val action = FragmentOneDirections.actionFragmentOneToFragmentFour()
            it.findNavController().navigate(action)
        }
    }

}