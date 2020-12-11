package com.example.cleanarchitectureandroid.utils

import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.google.android.material.imageview.ShapeableImageView

@BindingAdapter("setMovieImage")
fun ShapeableImageView.setMovieImageBackground(imageUrl: String) {
    Glide.with(this.context)
        .load(imageUrl)
        .into(this)
}
