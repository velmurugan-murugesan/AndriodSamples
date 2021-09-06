package com.imageuploader.base

import java.lang.ref.WeakReference

open class BasePresenter<out V: BaseView>(view: V) : BaseSubscriber() {

    private val reference: WeakReference<V> = WeakReference(view)

    fun getView() : V  {
        return reference.get()!!
    }

}
