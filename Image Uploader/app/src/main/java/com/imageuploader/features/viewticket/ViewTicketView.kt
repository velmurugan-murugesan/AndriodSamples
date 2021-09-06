package com.imageuploader.features.viewticket

import com.imageuploader.base.BaseView
import com.imageuploader.data.model.TicketDetailsByCriteriaTO

interface ViewTicketView : BaseView {

    fun updateTickets(tickets: List<TicketDetailsByCriteriaTO>?)
    fun showProgressbar()
    fun hideProgressbar()
}