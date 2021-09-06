package com.imageuploader.model.request

import com.google.gson.annotations.SerializedName

class RequestModel {

    data class Gallery(var cmt: String, var fileName: String)

    data class ImageUpload(
        @field:SerializedName("supID")
        var supID: String, @field:SerializedName("cmt")
        var cmt: String, @field:SerializedName("fileNmae")
        var fileNmae: String
    )

}