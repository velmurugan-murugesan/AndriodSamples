package com.example.cleanarchitectureandroid.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarchitectureandroid.data.Movie
import com.example.cleanarchitectureandroid.databinding.AdapterMovieBinding
import com.example.cleanarchitectureandroid.utils.ClickListener

class MovieAdapter : RecyclerView.Adapter<MovieViewHolder>() {

    var movieList = mutableListOf<Movie>()

    var clickListener: ClickListener<Movie>? = null

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val adapterMovieBinding = AdapterMovieBinding.inflate(layoutInflater, parent, false)
        return MovieViewHolder(adapterMovieBinding)
    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(movieList[position], this)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovies(movies: List<Movie>) {
        this.movieList = movies.toMutableList()
        notifyDataSetChanged()
    }

    fun setMovieClickListener(clickListener: ClickListener<Movie>) {
        this.clickListener = clickListener
    }

    fun onMovieItemClick(movie: Movie) {
        clickListener?.onCLick(movie)
    }

}

class MovieViewHolder(private val binding:AdapterMovieBinding) : RecyclerView.ViewHolder(binding.root) {

    fun bind(movie: Movie, movieAdapter: MovieAdapter) {
        binding.movie = movie
        binding.callback = movieAdapter
        binding.executePendingBindings()
    }
}
