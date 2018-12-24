package app.com.androidmvpbasic

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import app.com.androidmvpbasic.R.id.recyclerview_movie
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
        adapter = MovieAdapter()
        recyclerview_movie.layoutManager = LinearLayoutManager(this)
        recyclerview_movie.adapter = adapter

        presenter?.getMovieList()
    }


}
