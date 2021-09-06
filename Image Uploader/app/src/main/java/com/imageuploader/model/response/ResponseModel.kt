package com.imageuploader.model.response

import com.imageuploader.model.request.RequestModel

class ResponseModel {
    data class GalleryResponse (var heroes: List<RequestModel.Gallery>? = null)

    data class UploadResponse (var status: String, var message: String)

    data class PartNumber(var partNumber: String, var partName: String, var kanbanNumber: String,
                          var supplierCode: String, var expeditorID: String, var depoCode: String,
                          var partSequencedFlag: Boolean, var location: String)



}
