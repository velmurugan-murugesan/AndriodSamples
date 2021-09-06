package com.imageuploader.features.dashboard

import com.imageuploader.base.BaseView

interface DashboardView : BaseView {

    fun showProgressbar()
    fun hideProgressbar()
    fun updateTicketCount(ticketCount: Int)
    fun authIssue()
}