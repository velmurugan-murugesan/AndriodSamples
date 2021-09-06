package com.imageuploader.widgets

import android.content.Context
import androidx.constraintlayout.widget.ConstraintLayout
import android.util.AttributeSet
import android.view.View
import com.imageuploader.R
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.layout_toolbar.view.*

class AppToolbar(context: Context?, attrs: AttributeSet?) : ConstraintLayout(context, attrs) {

    lateinit var title: String
    var rightIconVisible: Boolean = false
    var leftIconVisible: Boolean = false

    var toolbarBackgroundColor = 0
    var rightIconBackground = 0
    var leftIconBackground = 0

    var clickListener : ToolbarClickListener? = null


    init {
        val attr = context?.obtainStyledAttributes(attrs, R.styleable.AppToolbar, 0,0)

        attr?.let {
            try {
                title = it.getString(R.styleable.AppToolbar_title)?: context.getString(R.string.app_name)
                rightIconVisible = it.getBoolean(R.styleable.AppToolbar_right_icon_visible,false)
                leftIconVisible = it.getBoolean(R.styleable.AppToolbar_left_icon_visible,false)
                toolbarBackgroundColor = it.getResourceId(R.styleable.AppToolbar_background_color, R.color.dark_blue)
                rightIconBackground = it.getResourceId(R.styleable.AppToolbar_right_icon_background, R.drawable.ic_image_black_24dp)
                leftIconBackground = it.getResourceId(R.styleable.AppToolbar_left_icon_background, R.drawable.ic_image_black_24dp)


            } finally {
                it.recycle()
            }
        }

        View.inflate(context, R.layout.layout_toolbar, this)
    }

    override fun onFinishInflate() {
        super.onFinishInflate()
        text_title.text = title
        img_right_icon.visible(rightIconVisible)
        img_left_icon.visible(leftIconVisible)
        toolbar_background.background = context.getDrawable(toolbarBackgroundColor)
        img_right_icon.background = context.getDrawable(rightIconBackground)
        img_left_icon.background = context.getDrawable(leftIconBackground)
        img_right_icon.setOnClickListener {
            clickListener?.onToolbarClick(it)
        }
        img_left_icon.setOnClickListener {
            clickListener?.onToolbarClick(it)
        }

    }

    fun setToolbarTitle(title: String) {
        text_title.text = title
    }

    fun setToolbarClickListener(clickListener: ToolbarClickListener) {
        this.clickListener = clickListener
    }

}