package com.imageuploader.features.imageupload

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.imageuploader.R
import com.imageuploader.base.BasePresenter
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.data.service.AuthApiService
import com.imageuploader.model.response.ResponseModel
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.text.SimpleDateFormat
import java.util.*

class ImageUploadPresenter(view: ImageUploadView) : BasePresenter<ImageUploadView>(view) {

    fun onPickImageFromCamera() {

        getView().onPickImageFromCamera()
    }

    fun onPickImageFromFolder() {
        getView().onPickImageFromFolder()
    }

    fun onImageSelect() {
        if (getView().checkPermission()) {
            getView().showPicSourceDialog()
        } else {
            getView().showMessage(R.string.provide_permission)
        }

    }

    fun onImageUpload(
        pictureFilePath: String?,
        selectedPaths: MutableList<String>,
        comment: String,
        isSelected: Boolean
    ) {
        if (pictureFilePath == null && selectedPaths.size == 0) {
            getView().showMessage(R.string.please_upload_files)
            return
        }

        if (comment.isEmpty()) {
            getView().showMessage(R.string.provide_comment)
            return
        }

        getView().progressStatus(true)
        val parts = mutableListOf<MultipartBody.Part>()
        if (isSelected) {
            selectedPaths.forEach {
                val file = File(it)
                val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
                val part = MultipartBody.Part.createFormData("files", file.name, fileReqBody)
                parts.add(part)
            }
        } else {
            val file = File(pictureFilePath)
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            val part = MultipartBody.Part.createFormData("files", file.name, fileReqBody)
            parts.add(part)

        }
        // uploadToServer(parts)


        val uploadAPIs = AuthApiService.createTicketService()
        val description = RequestBody.create(MediaType.parse("text/plain"), comment)
        val auth = AppPreference.getStringValue(AppConstants.KEY_ACCESS_TOKEN)
        val call = uploadAPIs.uploadImages( parts.toTypedArray(), description)
        call.enqueue(object : Callback<ResponseModel.UploadResponse> {
            override fun onResponse(
                call: Call<ResponseModel.UploadResponse>,
                response: Response<ResponseModel.UploadResponse>
            ) {

                when (response.code()) {

                    200 -> {
                        getView().onFileUploadSuccess()
                    }

                    else -> {
                        getView().onFileUploadFailed()
                    }
                }
            }

            override fun onFailure(call: Call<ResponseModel.UploadResponse>, t: Throwable) {
                getView().onFileUploadFailed()
            }
        })

    }


    fun getPictureFilePath(storageDir: File?): File? {
        var file: File? = null
        try {
            val timeStamp = SimpleDateFormat(AppConstants.DATE_FORMAT, Locale.US).format(Date())
            val pictureName = "subaru_$timeStamp"
            file = File.createTempFile(pictureName, ".jpg", storageDir)
        } catch (e: Exception) {

        }
        return file
    }

    fun handleActivityResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        pictureFilePath: String?
    ) {

        if (requestCode == AppConstants.PICTURE_CAPTURE_REQ_CODE && resultCode == Activity.RESULT_OK) {
            val imgFile = File(pictureFilePath)
            if (imgFile.exists()) {
                getView().updateCapturedImage(Uri.fromFile(imgFile))
            }
        }

        if (requestCode == AppConstants.PICK_MULTIPLE_IMAGE_REQ_CODE) {
            if (resultCode == AppCompatActivity.RESULT_OK) {
                var selectedPaths = mutableListOf<String>()

                if (data?.data != null) {
                    val mImageUri = data.data;
                    val filePath = getView().getFilePathByUri(mImageUri)
                    Log.d("filePath",filePath)
                    selectedPaths.add(filePath!!)
                } else {
                    val clipData = data?.clipData
                    if (clipData != null) { // handle multiple photo
                        selectedPaths = mutableListOf()
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            val filePath = getView().getFilePathByUri(uri)
                            filePath?.let {
                                selectedPaths.add(it)
                            }

                        }
                    }

                }
                if (selectedPaths.isNotEmpty()) {
                    getView().updateSelectedImage(selectedPaths)
                }
            }
        }
    }
}