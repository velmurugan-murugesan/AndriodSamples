package com.imageuploader.features.viewticket

import android.util.Log
import com.imageuploader.base.BasePresenter
import com.imageuploader.data.model.TicketResponse
import com.imageuploader.data.service.AuthApiService
import io.reactivex.observers.DisposableSingleObserver

class ViewTicketPresenter(val view: ViewTicketView) : BasePresenter<ViewTicketView>(view) {


    fun getTickets(criteria: String){
        getView()?.showProgressbar()
        subscribe(AuthApiService.createTicketService().fetchTicketsByCriteria( criteria),object :
            DisposableSingleObserver<TicketResponse>() {
            override fun onSuccess(value: TicketResponse?) {
                Log.d("TAG", "Success")
                value?.let { getView()?.updateTickets(it.content) }
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG", "Failed")
                getView()?.hideProgressbar()
            }
        })

    }


}