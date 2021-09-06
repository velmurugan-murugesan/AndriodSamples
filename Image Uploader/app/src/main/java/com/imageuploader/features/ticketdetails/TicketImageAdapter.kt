package com.imageuploader.features.ticketdetails

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.imageuploader.R
import kotlinx.android.synthetic.main.hero_item.view.*

class TicketImageAdapter : RecyclerView.Adapter<TicketImageAdapter.TicketImageAdapter>() {

    var imageUrls = mutableListOf<String>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TicketImageAdapter {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.hero_item, parent,false)
        return TicketImageAdapter(view)
    }

    override fun getItemCount(): Int {
        return imageUrls.size
    }

    override fun onBindViewHolder(holder: TicketImageAdapter, position: Int) {
        val url = imageUrls[position]

    }


    class TicketImageAdapter(view: View) : RecyclerView.ViewHolder(view) {

        val selectIcon = view.img_selected
        val imageSrc = view.hero_image

    }

}