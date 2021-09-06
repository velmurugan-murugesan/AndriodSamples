package com.imageuploader.helper

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter

import com.imageuploader.R
import com.imageuploader.data.model.PartInfo
import kotlinx.android.synthetic.main.layout_partnumber.view.*

class MyCustomAdapter(
    val mContext: Context, textViewResourceId: Int,
    val partInfoList: ArrayList<PartInfo>
)// TODO Auto-generated constructor stub
    : ArrayAdapter<PartInfo>(mContext, textViewResourceId,partInfoList) {

    override fun getDropDownView(
        position: Int, convertView: View?,
        parent: ViewGroup
    ): View {
        // TODO Auto-generated method stub
        return getCustomView(position, convertView, parent)
    }

    override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
        // TODO Auto-generated method stub
        return getCustomView(position, convertView, parent)
    }

    fun getCustomView(position: Int, convertView: View?, parent: ViewGroup): View {
        // TODO Auto-generated method stub
        //return super.getView(position, convertView, parent);

        val view = LayoutInflater.from(mContext).inflate(R.layout.layout_partnumber,parent,false)

        val partInfo = partInfoList[position]

        view.text_partnumber.text = partInfo.partNumber
        view.text_partname.text = partInfo.partName
        return view
    }
}