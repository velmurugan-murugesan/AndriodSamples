package com.imageuploader.features.dashboard

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imageuploader.R
import com.imageuploader.interfaces.ClickListener
import kotlinx.android.synthetic.main.adapter_dashboard.view.*

class DashbaordAdapter : RecyclerView.Adapter<DashboardViewHolder>() {

    var menu_name = mutableListOf<String>()
    var menu_icon = mutableListOf<Int>()
    var ticketCount = 0
    var isShowTicketCount = false
    var clickListener: ClickListener? = null

    init {
        menu_name.add("Create Ticket")
        menu_name.add("View Ticket")
        menu_name.add("Upload Image")
        menu_name.add("View Gallery")

        menu_icon.add(R.drawable.create_icon)
        menu_icon.add(R.drawable.view_ticket)
        menu_icon.add(R.drawable.upload_icon)
        menu_icon.add(R.drawable.gallery_icon)

    }

    fun showTicketCount(count: Int) {
        this.ticketCount = count
        this.isShowTicketCount = true
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.adapter_dashboard, parent, false)
        return DashboardViewHolder(view)
    }

    override fun getItemCount(): Int {
        return menu_name.size
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {

        holder.name.text = menu_name[position]
        holder.image.background = holder.itemView.context.getDrawable(menu_icon[position])
        holder.layout.setOnClickListener {
            clickListener?.onClickItem(it,position, menu_name[position])
        }

        if(isShowTicketCount && position == 1) {
            holder.notificaionIcon.visibility = View.VISIBLE
            holder.count.visibility = View.VISIBLE
            holder.count.text = ticketCount.toString()
        } else {
            holder.count.visibility = View.GONE
            holder.notificaionIcon.visibility = View.GONE
        }
    }

    fun addItemClickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }

}

class DashboardViewHolder(view:View) : RecyclerView.ViewHolder(view) {

    val layout =  view.menu_layout
    val image = view.img_menu_icon
    val name = view.text_menu_name
    val notificaionIcon = view.notification_icon
    val count = view.text_ticket_count

}
