package com.velmurugan.materialmotionanimationexample

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.material.transition.MaterialFadeThrough
import com.velmurugan.materialmotionanimationexample.databinding.HomeFragmentBinding
import com.velmurugan.materialmotionanimationexample.databinding.SearchFragmentBinding

class SearchFragment : Fragment() {
    lateinit var searchBinding: SearchFragmentBinding
    lateinit var adapter: MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        searchBinding = SearchFragmentBinding.inflate(layoutInflater, container, false)
        return searchBinding.root
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enterTransition = MaterialFadeThrough()

        adapter = MovieAdapter()
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }
}