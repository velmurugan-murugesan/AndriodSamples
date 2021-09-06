package com.imageuploader.features.viewticket

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.imageuploader.R
import com.imageuploader.interfaces.ListClickListener
import kotlinx.android.synthetic.main.fragment_filter_sheet.*

class FilterSheetFragment : BottomSheetDialogFragment(),View.OnClickListener {

    override fun onClick(v: View?) {
        listener?.onClick(v!!, "")
    }

    private var listener: ListClickListener<String>? = null

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_filter_sheet, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        text_sort_name.setOnClickListener(this)
        text_sort_date.setOnClickListener(this)
        text_sort_cancel.setOnClickListener(this)
    }

    fun setOnItemClickListener(listClickListener: ListClickListener<String>) {
        this.listener = listClickListener
    }

}