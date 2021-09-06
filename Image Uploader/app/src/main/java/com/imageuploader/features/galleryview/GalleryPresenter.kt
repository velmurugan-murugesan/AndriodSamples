package com.imageuploader.features.galleryview

import com.imageuploader.base.ErrorHandlerPresenter
import com.imageuploader.base.ErrorHandlerView
import com.imageuploader.data.service.AuthApiService
import io.reactivex.observers.DisposableSingleObserver

class GalleryPresenter(val view : GalleryView ) : ErrorHandlerPresenter<GalleryView>(view) {

    fun loadGalleryItems() {
        val apiService = AuthApiService.createTicketService()
        subscribe(apiService.getAllImagesByCmt(), object : DisposableSingleObserver<Map<String, List<String>>>(){
            override fun onSuccess(value: Map<String, List<String>>?) {
                value?.let {
                    view.loadGalleryItems(value)
                }
            }

            override fun onError(e: Throwable?) {
                e?.let {
                    onErrorHandler(it)
                }
            }
        })
    }

}

interface GalleryView : ErrorHandlerView {

    fun loadGalleryItems(items : Map<String, List<String>>)

}
