package com.example.kotlinhiltexample.ui.main

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.kotlinhiltexample.Movie
import com.example.kotlinhiltexample.databinding.AdapterMoviesBinding
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.scopes.ActivityScoped
import javax.inject.Inject

@ActivityScoped
class MovieAdapter @Inject constructor(@ActivityContext private val context: Context) : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(context)
        val adapterMoviesBinding = AdapterMoviesBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(adapterMoviesBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovies(data: List<Movie>) {
        this.movieList = data.toMutableList()
        notifyDataSetChanged()
    }

}

class MovieViewHolder(private val view: AdapterMoviesBinding) : RecyclerView.ViewHolder(view.root) {

    fun bind(movie: Movie) {
        view.movie = movie
        view.executePendingBindings()
    }

}
