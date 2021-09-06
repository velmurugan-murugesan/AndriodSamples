package com.imageuploader.features.dashboard

import android.util.Log
import com.imageuploader.base.BasePresenter
import com.imageuploader.data.model.TicketCountResponse
import com.imageuploader.data.service.AuthApiService
import io.reactivex.observers.DisposableSingleObserver

class DashboardPresenter(view: DashboardView) : BasePresenter<DashboardView>(view) {

    fun fetchTicketCount() {

        getView()?.showProgressbar()

        subscribe(AuthApiService.createTicketService().fetchTicketsByCriteriaView(), object : DisposableSingleObserver<TicketCountResponse>() {
            override fun onSuccess(value: TicketCountResponse?) {
                Log.d("TAG", "Success")
                value?.let {
                    getView()?.updateTicketCount(value.content)
                }
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG", "Failed")
                getView()?.authIssue()
                getView()?.hideProgressbar()
            }

        })

        //getView()?.updateTicketCount(5)
    }

}