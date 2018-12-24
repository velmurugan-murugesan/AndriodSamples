package app.com.androidmvpbasic

interface MainView : BaseView{
    fun updateMovieList(t: List<Movie>)
}