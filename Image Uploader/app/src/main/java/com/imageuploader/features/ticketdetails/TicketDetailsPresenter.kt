package com.imageuploader.features.ticketdetails

import android.util.Log
import com.imageuploader.base.BasePresenter
import com.imageuploader.data.model.TicketDetailsForm
import com.imageuploader.data.service.AuthApiService
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.MediaType
import okhttp3.RequestBody

class TicketDetailsPresenter(view: TicketDetailsView) : BasePresenter<TicketDetailsView>(view) {

    fun fetchTicketDetails(ticketId: String) {
        getView()?.showProgressbar()
        val description = RequestBody.create(MediaType.parse("text/plain"), ticketId)
        subscribe(AuthApiService.createTicketService().fetchTicketDetails(description),object :
            DisposableSingleObserver<TicketDetailsForm>() {
            override fun onSuccess(value: TicketDetailsForm?) {
                Log.d("TAG", "Success")
                getView()?.updateTicketDetails(value)
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG", "Failed")
                getView()?.hideProgressbar()
            }
        })
    }

}