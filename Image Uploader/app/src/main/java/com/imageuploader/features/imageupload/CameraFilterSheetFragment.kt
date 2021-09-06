package com.imageuploader.features.imageupload

import android.os.Bundle
import androidx.annotation.ColorInt
import android.view.ViewGroup
import android.view.LayoutInflater
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import android.view.View
import com.imageuploader.R
import com.imageuploader.interfaces.ListClickListener
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.layout_select_image.*

class CameraFilterSheetFragment(private val uploadOption: Boolean) : BottomSheetDialogFragment(), View.OnClickListener {

    private var listener: ListClickListener<String>? = null
    private var state : String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.layout_select_image, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        img_folder.setOnClickListener(this)
        text_folder.setOnClickListener (this)
        img_camera.setOnClickListener(this)
        text_camera.setOnClickListener(this)
        img_gallery.setOnClickListener(this)
        text_gallery.setOnClickListener(this)
        if(!uploadOption) {
            img_gallery.visible(false)
            text_gallery.visible(false)

        }

        /*text_not_answered.setOnClickListener {
            setState(AppConstants.NOT_ANSWERED)
            listener?.onClick(text_answered,AppConstants.NOT_ANSWERED)
        }

        text_answered.setOnClickListener {
            setState(AppConstants.ANSWERED)
            listener?.onClick(text_answered, AppConstants.ANSWERED)
        }*/


      /*  state?.let {
            setState(it)
        }*/
    //    selectAnswered(STATE.SELECTED)
    }

    override fun onClick(v: View?) {

        when(v?.id) {

            R.id.text_gallery,R.id.img_gallery -> {
                listener?.onClick(v, AppConstants.KEY_UPLOAD)
            }

            R.id.img_camera, R.id.text_camera -> {
                listener?.onClick(v, AppConstants.KEY_CAMERA)
            }

            R.id.img_folder, R.id.text_folder -> {
                listener?.onClick(v, AppConstants.KEY_FOLDER)
            }
        }

    }


  /*  private fun setState(state: String) {
        if(state == AppConstants.ANSWERED) {
            selectAnswered(STATE.SELECTED)
            selectNotAnswered(STATE.NOT_SELECTED)
        } else {
            selectNotAnswered(STATE.SELECTED)
            selectAnswered(STATE.NOT_SELECTED)
        }

    }


    private fun selectAnswered(state: STATE) {
        img_answered.setBackgroundTintColor(state.colorInt)
        text_answered.setTextViewColor(state.colorInt)
    }

    private fun selectNotAnswered(state: STATE) {
        img_not_answered.setBackgroundTintColor(state.colorInt)
        text_not_answered.setTextViewColor(state.colorInt)
    }*/

    fun setOnItemClickListener(listClickListener: ListClickListener<String>) {
        this.listener = listClickListener
    }

    enum class STATE(@ColorInt colorPrimary: Int) {
        SELECTED(R.color.colorPrimary),
        NOT_SELECTED(R.color.text_color);
        var colorInt: Int = colorPrimary
    }

}