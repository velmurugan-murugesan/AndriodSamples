package com.imageuploader.base

import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers

open class BaseSubscriber {

    fun <T> subscribe(single: Single<T>, observer: DisposableSingleObserver<T>) {
        single.observeOn(AndroidSchedulers.mainThread())
            .subscribeOn(Schedulers.io())
            .subscribeWith(observer)
    }

}
