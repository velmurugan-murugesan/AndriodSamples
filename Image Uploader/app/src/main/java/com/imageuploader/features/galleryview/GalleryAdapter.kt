package com.imageuploader.features.galleryview

import android.content.Context
import android.os.Build
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.annotation.RequiresApi
import com.imageuploader.R
import com.imageuploader.interfaces.ClickListener
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.new_item.view.*


class GalleryAdapter(private val context: Context, private val map: Map<String, List<String>>, private val isUpload: Boolean) :
    androidx.recyclerview.widget.RecyclerView.Adapter<GalleryAdapter.MyViewHolder>() {

    var keys: List<String> = map.keys.toList()
    var galleryAdapter = GalleryImageAdapter(context)
    var galleryClickListener: GalleryClickListener? = null

    var selectedItems = mutableMapOf<String, Set<String>>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, p1: Int): MyViewHolder {
        //val view = LayoutInflater.from(viewGroup.context).inflate(R.layout.new_item, null)

        val view =
            LayoutInflater.from(viewGroup.context).inflate(R.layout.new_item, viewGroup, false)
        return MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return keys.size
    }

    override fun onBindViewHolder(holder: MyViewHolder, p1: Int) {
        holder.heroImage.text = keys[holder.adapterPosition]

        val itemListDataAdapter = GalleryImageAdapter(context)
        holder.recyclerView.setHasFixedSize(true)
        holder.recyclerView.layoutManager =
            androidx.recyclerview.widget.GridLayoutManager(context, 4)
        holder.recyclerView.setAdapter(itemListDataAdapter)
        holder.imgCreateTicket.setOnClickListener {

            galleryClickListener?.onCreateClick(it, map.get(keys[holder.adapterPosition])!!)
        }

        itemListDataAdapter.setHeros(map.get(keys[holder.adapterPosition])!!.toMutableList(), false)

        if(isUpload) {
            holder.imgCreateTicket.visible(false)
        } else {
            holder.imgCreateTicket.visible(true)
        }
        itemListDataAdapter.setOnclickListener(object : ClickListener {
            @RequiresApi(Build.VERSION_CODES.N)
            override fun onClickItem(view: View, id: Int, data: String) {

                if(!isUpload) {
                    galleryClickListener?.onclick(view,data,keys[holder.adapterPosition])

                } else {
                    val data2: Set<String>? = selectedItems[keys[holder.adapterPosition]]
                    data2?.let {
                        val ss = data2.toMutableSet()
                        if(ss.indexOf(data) >= 0) {
                            ss.remove(data)
                        } else {
                            ss.add(data)
                        }

                        selectedItems[keys[holder.adapterPosition]] = ss
                        selectedItems.forEach { s, arrayList ->

                            Log.d("$s ", "$arrayList")
                        }
                    } ?: kotlin.run {
                        selectedItems[keys[holder.adapterPosition]] = setOf(data)
                    }
                    itemListDataAdapter.setSelectedItem(selectedItems)
                }



            }
        })
    }

    fun setGalleryClickLisener(galleryClickListener: GalleryClickListener) {
        this.galleryClickListener = galleryClickListener
    }

    inner class MyViewHolder internal constructor(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val heroImage: TextView = itemView.text_title
        val recyclerView: androidx.recyclerview.widget.RecyclerView = itemView.recyclerview_images
        val imgCreateTicket = itemView.img_create_ticket
    }
}


interface GalleryClickListener {
    fun onclick(view: View, url: String, cmd: String)
    fun onCreateClick(view: View, urls: List<String>)
}