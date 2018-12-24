package app.com.androidmvpbasic

import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import app.com.androidmvpbasic.MovieAdapter.MyViewHolder

class MovieAdapter : RecyclerView.Adapter<MyViewHolder>() {

    var movieList = mutableListOf<Movie>()

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
        val movie = movieList[p1]
        holder.movieTittle.text = movie.name
    }

    class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val movieTittle = item.findViewById<TextView>(R.id.text_movie_title)

    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): MyViewHolder {
        val view = LayoutInflater.from(p0.context).inflate(R.layout.movie_adapter_list,p0,false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun addMovieList(t: List<Movie>) {
        movieList = t.toMutableList()
        notifyDataSetChanged()
    }

}