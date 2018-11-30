package app.com.robolecticstesting

open class BasePresenter<T: BaseView> {

    lateinit var view : T

    fun attach(v: T){
        view = v
    }

    fun getV(): T {
        return view
    }
}