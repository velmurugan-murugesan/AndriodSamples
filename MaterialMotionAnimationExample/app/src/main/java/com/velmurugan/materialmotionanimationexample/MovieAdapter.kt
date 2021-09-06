package com.velmurugan.materialmotionanimationexample

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.velmurugan.materialmotionanimationexample.databinding.AdapterMoviesBinding

class MovieAdapter : RecyclerView.Adapter<MenuViewHolder>() {

    private var movieList = mutableListOf<Movie>()
    var clickInterface: ClickInterface<Movie>? = null


    fun addMovieList(menuList: List<Movie>) {
        this.movieList = menuList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MenuViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterMoviesBinding.inflate(inflater, parent, false)
        return MenuViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MenuViewHolder, position: Int) {
        val movie = movieList[position]

        holder.adapterPendingOrderBinding.apply {


            textTitle.text = movie.name
            textTag.text = movie.category
            cardSmall.transitionName = "${movie.name}"
            Glide.with(holder.itemView.context).load(movie.imageUrl)
                .apply(
                    RequestOptions()
                )
                .centerCrop()
                .into(imgMovie)

            cardSmall.setOnClickListener {
                clickInterface?.onClick(movie,position,it)
            }
        }


    }

    override fun getItemCount(): Int {
        return movieList.size
    }

    fun setMovieClickListener(clickInterface: ClickInterface<Movie>) {
        this.clickInterface = clickInterface
    }

}

class MenuViewHolder(val adapterPendingOrderBinding: AdapterMoviesBinding) :
    RecyclerView.ViewHolder(adapterPendingOrderBinding.root)

interface ClickInterface<T> {
    fun onClick(data: T, position: Int, view: View)
}