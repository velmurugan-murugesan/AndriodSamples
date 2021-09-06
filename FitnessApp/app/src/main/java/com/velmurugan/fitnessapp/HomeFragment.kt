package com.velmurugan.fitnessapp

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.velmurugan.fitnessapp.databinding.HomeBinding

class HomeFragment : Fragment() {

    private lateinit var modelFragmentBinding: HomeBinding

    lateinit var adapter: HeatMapAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        modelFragmentBinding = HomeBinding.inflate(layoutInflater, container, false)
        return modelFragmentBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        adapter = HeatMapAdapter()
        modelFragmentBinding.recyclerview.adapter = adapter

        adapter.addModelList(getHeatMapData())

    }


    fun getHeatMapData() : List<HeatMap>{
        val data = mutableListOf<HeatMap>()
        data.add(HeatMap(R.color.heat_600))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_800))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_900))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_600))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_900))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_600))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_900))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_600))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_300))
        data.add(HeatMap(R.color.heat_900))
        data.add(HeatMap(R.color.heat_200))
        data.add(HeatMap(R.color.heat_400))
        data.add(HeatMap(R.color.heat_700))
        data.add(HeatMap(R.color.heat_400))
        return data
    }
}
