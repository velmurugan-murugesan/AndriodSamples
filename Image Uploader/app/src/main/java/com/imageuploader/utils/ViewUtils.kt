package com.imageuploader.utils

import android.content.res.ColorStateList
import androidx.core.view.ViewCompat
import android.widget.ImageView
import android.widget.TextView
import com.imageuploader.base.MyApp
import com.imageuploader.utils.setTextViewColor

object ViewUtils {

    @JvmStatic
    fun setBackgroundTintColor(view:ImageView, color: Int){
        ViewCompat.setBackgroundTintList(view, ColorStateList.valueOf(MyApp.instance.getColor(color)))
    }

    @JvmStatic
    fun setTextColor(view: TextView, color: Int) {
        view.setTextViewColor(color)
    }

}