package com.imageuploader.features.viewticket

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.model.TicketDetailsByCriteriaTO
import com.imageuploader.interfaces.ListClickListener
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.features.ticketdetails.TicketDetailsActivity
import kotlinx.android.synthetic.main.activity_image_upload.toolbar
import kotlinx.android.synthetic.main.activity_view_ticket.*
import android.content.Context
import android.view.inputmethod.InputMethodManager

class ViewTicketActivity : BaseActivity(), ViewTicketView {

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun updateTickets(tickets: List<TicketDetailsByCriteriaTO>?) {

        tickets?.let {
            viewTicketAdapter.setTickets(it.reversed())
        }
        hideProgressbar()
        hideSoftKeyboard()

    }


    lateinit var viewTicketAdapter: ViewTicketAdapter
    lateinit var presenter: ViewTicketPresenter
    lateinit var filterSheetFragment: FilterSheetFragment

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_view_ticket)
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

        searchView.isActivated = true
        searchView.queryHint = getString(R.string.search)
        searchView.onActionViewExpanded()
        searchView.isIconified = false
        searchView.setQuery("", false)

        searchView.setOnQueryTextListener(object :
            android.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String): Boolean {
                return false
            }

            override fun onQueryTextChange(newText: String): Boolean {
                viewTicketAdapter.filter.filter(newText)
                return false
            }
        })

        viewTicketAdapter = ViewTicketAdapter()
        recyclerview_tickets.layoutManager = LinearLayoutManager(this)
        recyclerview_tickets.adapter = viewTicketAdapter
        viewTicketAdapter.setClickListener(object : ListClickListener<TicketDetailsByCriteriaTO>{
            override fun onClick(view: View, data: TicketDetailsByCriteriaTO) {
                val bundle = Bundle()
                bundle.putString(AppConstants.KEY_TICKET_ID, data.tagNumber.toString())
                //launchActivity<TicketDetailsActivity>(bundle)

                val intent = Intent(this@ViewTicketActivity, TicketDetailsActivity::class.java)
                intent.putExtra(AppConstants.KEY_TICKET_ID,data.tagNumber.toString())
                startActivity(intent)
                overridePendingTransitionEnter()
            }
        })

        img_sort.setOnClickListener {
            filterSheetFragment.show(supportFragmentManager, filterSheetFragment.tag);
        }

        filterSheetFragment = FilterSheetFragment()


        filterSheetFragment.setOnItemClickListener(object : ListClickListener<String> {
            override fun onClick(view: View, data: String) {

                when(view.tag) {

                    "NAME" -> {

                        val item = viewTicketAdapter.getItems()
                        item.sortBy {
                            it.partName
                        }
                        viewTicketAdapter.setTickets(item)

                    }

                    "DATE" -> {
                        val item = viewTicketAdapter.getItems()
                        item.sortBy {
                            it.createdDate
                        }
                        viewTicketAdapter.setTickets(item)
                    }

                    else -> {

                    }

                }
                filterSheetFragment.dismiss()
            }
        })
        presenter = ViewTicketPresenter(this)
        presenter.getTickets("assignedToYou")

    }

    fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransitionExit()
    }

}