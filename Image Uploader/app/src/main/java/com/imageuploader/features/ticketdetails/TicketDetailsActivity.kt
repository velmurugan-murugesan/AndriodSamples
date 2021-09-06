package com.imageuploader.features.ticketdetails

import android.os.Bundle
import android.view.View
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.model.TicketDetailsForm
import com.imageuploader.features.galleryview.GalleryImageAdapter
import com.imageuploader.interfaces.ToolbarClickListener
import kotlinx.android.synthetic.main.activity_ticket_details.*
import kotlinx.android.synthetic.main.activity_ticket_details.toolbar

class TicketDetailsActivity : BaseActivity(),
    TicketDetailsView {

    lateinit var adapter: GalleryImageAdapter

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun updateTicketDetails(ticketDetailsForm: TicketDetailsForm?) {

        val ticket = ticketDetailsForm?.content

        ticket?.let {

            toolbar.setToolbarTitle("Basic Details - ${it.tagNumber}")
            reporting_associate.text = it.reportingAssociate
            type_of_defect.text = it.defectType
            defect_cause.text = it.defectCause
            defect_description.text = it.defectDesc
            assigned_to.text = "${it.groupLeaderReviewID} - ${it.groupLeaderReviewName}"
            date_created.text = it.dateCreated
            part_number.text = it.partNumber?.replace("\n","")
            part_name.text = it.partName
            kanban_number.text = it.kanbanNumber
            section.text = it.currentSectionDesc
            supplier_code.text = it.supplierCode
            supplier_name.text = it.supplierName
            cost_code.text = it.basicCostCode
            line_code.text = it.lineCode
            sequence_number.text = it.sequenceNumber
            quality.text = it.quantity.toString()





        }

        hideProgressbar()
        }

    lateinit var presenter: TicketDetailsPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_ticket_details)

       // adapter = GalleryImageAdapter(this)
        toolbar.setToolbarClickListener(object : ToolbarClickListener {
            override fun onToolbarClick(view: View) {
                when (view.id) {

                    R.id.img_left_icon-> {
                        finish()
                        overridePendingTransitionExit()
                    }
                }
            }
        })
        val ticketId: String? = intent?.getStringExtra(AppConstants.KEY_TICKET_ID)

        if(!ticketId.isNullOrEmpty()) {
            presenter = TicketDetailsPresenter(this)
            presenter.fetchTicketDetails(ticketId)
        }
    }

}