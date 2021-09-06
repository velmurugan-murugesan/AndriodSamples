package com.imageuploader.features.viewticket

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.recyclerview.widget.RecyclerView
import com.imageuploader.R
import com.imageuploader.data.model.TicketDetailsByCriteriaTO
import com.imageuploader.interfaces.ListClickListener
import kotlinx.android.synthetic.main.adapter_view_ticket.view.*

class ViewTicketAdapter : RecyclerView.Adapter<ViewTicketViewHolder>(), Filterable {
    var valueFiler: ValueFiler? = null

    var ticket = mutableListOf<TicketDetailsByCriteriaTO>()
    var listClickListener: ListClickListener<TicketDetailsByCriteriaTO>? = null
    var mData: List<TicketDetailsByCriteriaTO> = mutableListOf()

    fun setTickets(tickets: List<TicketDetailsByCriteriaTO>) {
        this.ticket = tickets.toMutableList()
        this.mData = tickets

        notifyDataSetChanged()
    }

    override fun getFilter(): Filter {

        if(valueFiler == null) {
            valueFiler = ValueFiler()
        }
        return valueFiler as ValueFiler
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewTicketViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_view_ticket, parent, false)
        return ViewTicketViewHolder(view)
    }

    override fun getItemCount(): Int {
        return mData.size
    }

    override fun onBindViewHolder(holder: ViewTicketViewHolder, position: Int) {
        val item = mData[position]
        holder.ticketTitle.text = item.tagNumber.toString()
        holder.partNumber.text = item.partNumber?.replace("\n","").toString()
        holder.date.text = item.createdDate

        holder.tickeContainer.setOnClickListener { view ->
            listClickListener?.onClick(view, item)
        }

    }

    inner class ValueFiler : Filter() {

        override fun performFiltering(constraint: CharSequence?): FilterResults {

            val results = Filter.FilterResults()

            if (constraint != null && constraint.isNotEmpty()) {
                val filterList = mutableListOf<TicketDetailsByCriteriaTO>()
                for (i in 0 until ticket.size) {
                    if (ticket[i].tagNumber.toString().contains(constraint.toString().toUpperCase()) || ticket[i].partName.toString().contains(constraint.toString()) ) {
                        filterList.add(ticket.get(i))
                    }
                }
                results.count = filterList.size
                results.values = filterList
            } else {
                results.count = ticket.size
                results.values = ticket
            }
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            mData = results?.values as MutableList<TicketDetailsByCriteriaTO>
            notifyDataSetChanged()
        }

    }

    fun setClickListener(listClickListener: ListClickListener<TicketDetailsByCriteriaTO>) {
        this.listClickListener = listClickListener
    }

    fun getItems(): MutableList<TicketDetailsByCriteriaTO> {
        return ticket

    }
}

class ViewTicketViewHolder(view: View) : RecyclerView.ViewHolder(view) {
    val tickeContainer = view.ticket_container
    val ticketTitle = view.ticket_number
    val partNumber = view.part_number
    val date = view.date
}
