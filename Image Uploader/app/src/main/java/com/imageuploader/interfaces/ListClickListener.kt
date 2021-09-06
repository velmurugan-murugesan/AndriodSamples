package com.imageuploader.interfaces

import android.view.View

interface ListClickListener<VO> {
    fun onClick(view: View, data: VO)
}