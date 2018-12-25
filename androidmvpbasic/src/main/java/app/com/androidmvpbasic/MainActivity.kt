package app.com.androidmvpbasic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.view.View
import android.widget.Toast
import app.com.androidmvpbasic.R.id.recyclerview_movie
import app.com.androidmvpbasic.callbacks.RecyclerviewCallbacks
import app.com.androidmvpbasic.data.RealmHandler
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {


    override fun updateMovieList(t: List<Movie>) {
        adapter?.addMovieList(t)
    }

    private var presenter: MainPresenter? = null
    private var adapter: MovieAdapter? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        presenter = MainPresenter()
        presenter?.attach(this)
        adapter = MovieAdapter(this)
        recyclerview_movie.layoutManager = LinearLayoutManager(this)

        adapter?.setOnClick(object : RecyclerviewCallbacks{
            override fun onItemClick(view: View, position: Int) {
                Toast.makeText(baseContext,"$position",Toast.LENGTH_SHORT).show()
                RealmHandler().addMovie(adapter!!.movieList[position])
            }
        })
        recyclerview_movie.adapter = adapter

        presenter?.getMovieList()

    }


}
