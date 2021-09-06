package com.velmurugan.fitnessapp

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.velmurugan.fitnessapp.databinding.AdapterHeatMapBinding

class HeatMapAdapter  : RecyclerView.Adapter<ModelViewHolder>() {

    private var modelList = mutableListOf<HeatMap>()

    fun addModelList(menuList: List<HeatMap>) {
        this.modelList = menuList.toMutableList()
        notifyDataSetChanged()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ModelViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = AdapterHeatMapBinding.inflate(inflater, parent, false)
        return ModelViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ModelViewHolder, position: Int) {

        val item = modelList[position]

        holder.adapterModelBinding.heatView.setBackgroundResource(item.color)


    }


    override fun getItemCount(): Int {
        return modelList.size
    }


}

class ModelViewHolder(val adapterModelBinding: AdapterHeatMapBinding): RecyclerView.ViewHolder(adapterModelBinding.root)
