package com.imageuploader.widgets

import android.app.AlertDialog
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.widget.Button
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.imageuploader.R
import kotlinx.android.synthetic.main.my_alert_layout.*
import kotlinx.android.synthetic.main.my_alert_layout.view.*

class MyAlertDialog constructor(context: Context) : AlertDialog(context) {

    private var tvTitle: TextView? = null
    private var tvDesc: TextView? = null
    private var btnLeft: Button? = null
    private var btnRight: Button? = null
    private var btnMiddle: Button? = null
    private var layoutTwoButtons: LinearLayout? = null
    private var layoutOneButton: LinearLayout? = null
    private val isInfoMode = false
    private var alertIcon: ImageView? = null
    private var dialogActionListener: DialogActionListener? = null

    private val mOnClickListener = View.OnClickListener { v -> dialogActionListener!!.onAction(v) }

    init {
        initialize()
    }

    private fun initialize() {
        val view = LayoutInflater.from(context).inflate(R.layout.my_alert_layout, null)
        window!!.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        tvTitle = view.findViewById(R.id.tv_title)
        tvDesc = view.findViewById(R.id.tv_desc)
        alertIcon = view.findViewById(R.id.alert_icon)


        btnLeft = view.btn_left
        btnMiddle = view.btn_middle
        btnRight = view.btn_right

        layoutOneButton = view.layout_one_button
        layoutTwoButtons = view.layout_two_button

        setClickListener(view.btn_left, view.btn_right, view.btn_middle)
        setView(view)
    }

    private fun setClickListener(vararg views: View) {
        for (view in views) {
            view.setOnClickListener(mOnClickListener)
        }
    }

    fun setOnActionListener(dialogActionListener: DialogActionListener) {
        this.dialogActionListener = dialogActionListener
    }

    fun setAlertTitle(title: String?) {
        if (title != null) {
            tvTitle!!.visibility = View.VISIBLE
            tvTitle!!.text = title
        }
    }

    fun setAlertDesciption(desciption: String) {
        tvDesc!!.visibility = View.VISIBLE
        tvDesc!!.text = desciption
    }

    fun setRightButtonText(text: String) {
        layoutTwoButtons!!.visibility = View.VISIBLE
        layoutOneButton!!.visibility = View.GONE
        btnRight!!.visibility = View.VISIBLE
        btnRight!!.text = text
    }

    fun setLeftButtonText(text: String) {
        layoutTwoButtons!!.visibility = View.VISIBLE
        layoutOneButton!!.visibility = View.GONE
        btnLeft!!.visibility = View.VISIBLE
        btnLeft!!.text = text
    }

    fun setMiddleButtonText(text: String) {
        layoutTwoButtons!!.visibility = View.GONE
        layoutOneButton!!.visibility = View.VISIBLE
        btnMiddle!!.text = text
    }

    fun setAlertIcon(drawable: Drawable) {
        alertIcon!!.visibility = View.VISIBLE
        alertIcon!!.background = drawable
    }

    interface DialogActionListener {
        fun onAction(viewId: View)
    }

}