package com.imageuploader.features.ticketdetails

import com.imageuploader.base.BaseView
import com.imageuploader.data.model.TicketDetailsForm

interface TicketDetailsView : BaseView {

    fun updateTicketDetails(ticketDetailsForm: TicketDetailsForm?)
    fun showProgressbar()
    fun hideProgressbar()
}