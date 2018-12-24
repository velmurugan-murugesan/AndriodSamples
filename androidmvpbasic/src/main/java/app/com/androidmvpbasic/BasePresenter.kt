package app.com.androidmvpbasic

import io.reactivex.Single
import io.reactivex.SingleObserver
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

open class BasePresenter<T:BaseView> {

    lateinit var view : T

    fun attach(v: T){
        view = v
    }

    fun getV(): T {
        return view
    }

    fun <O> subscribe(single: Single<O>, observable: SingleObserver<O> ) {

        single.subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.computation())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(observable)
    }
}