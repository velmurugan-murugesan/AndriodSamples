package com.imageuploader.features.imageupload

import android.net.Uri
import com.imageuploader.base.BaseView

interface ImageUploadView : BaseView {

    fun onPickImageFromCamera()
    fun onPickImageFromFolder()
    fun showMessage(message: Int)
    fun checkPermission() : Boolean
    fun showPicSourceDialog()
    fun progressStatus(status: Boolean)
    fun onFileUploadSuccess()
    fun onFileUploadFailed()
    fun updateCapturedImage(fromFile: Uri?)
    fun getFilePathByUri(mImageUri: Uri?): String?
    fun updateSelectedImage(selectedPaths: MutableList<String>)

}