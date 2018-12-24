package app.com.androidmvpbasic

import android.util.Log
import io.reactivex.SingleObserver
import io.reactivex.disposables.Disposable

class MainPresenter() : BasePresenter<MainView>() {



    fun getMovieList() {
        val movieList = ApiInterface.create().movieList()
        subscribe(movieList, object : SingleObserver<List<Movie>> {
            override fun onSubscribe(d: Disposable) {

            }

            override fun onSuccess(t: List<Movie>) {
                Log.d("onSuccess", t.toString())
                getV().updateMovieList(t)
            }

            override fun onError(e: Throwable) {
                Log.d("onError", e.message)
            }
        })

    }

}