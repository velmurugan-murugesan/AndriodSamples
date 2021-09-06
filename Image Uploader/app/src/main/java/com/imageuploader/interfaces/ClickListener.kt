package com.imageuploader.interfaces

import android.view.View

interface ClickListener {
    fun onClickItem(view: View, id: Int, data: String)
}
