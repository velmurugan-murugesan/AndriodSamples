package app.com.mvvmsample.view

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import app.com.mvvmsample.view.MovieAdapter.MyViewHolder
import app.com.mvvmsample.R
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions

class MovieAdapter(val context: Context) : RecyclerView.Adapter<MyViewHolder>() {

    var movieList = mutableListOf<Movie>()

    var callback: RecyclerviewCallbacks? = null

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
        val movie = movieList[p1]
        holder.movieTittle.text = movie.name
        Glide.with(context)
                .load(movie.imageUrl)
                .apply(RequestOptions().circleCrop())
                .into(holder.imageMovie)
    }

    fun setOnClick(click: RecyclerviewCallbacks){
        callback = click
    }

    inner class MyViewHolder(item: View) : RecyclerView.ViewHolder(item) {

        val movieTittle = item.findViewById<TextView>(R.id.text_movie_title)
        val imageMovie = item.findViewById<ImageView>(R.id.image_movie)

        init {
            setClickListener(movieTittle)
            setClickListener(imageMovie)
        }

        private fun setClickListener(view: View) {
            view.setOnClickListener {
                callback?.onItemClick(it, adapterPosition)
            }
        }

        private val clickListener1 = View.OnClickListener { v ->
            val position = adapterPosition

        }



        private val clickListener = View.OnClickListener {
            callback?.onItemClick(it, adapterPosition)
        }

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