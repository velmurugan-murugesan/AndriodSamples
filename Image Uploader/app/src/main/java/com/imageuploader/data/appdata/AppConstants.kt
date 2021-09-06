package com.imageuploader.data.appdata

import android.Manifest
import com.imageuploader.BuildConfig

object AppConstants {
    //const val BASE_AUTH_URL: String = "http://172.27.0.123:8080/"
    //const val BASE_AUTH_URL: String = "http://172.27.27.88:8080/"

    const val BASE_AUTH_TICKET: String = "http://172.27.27.89:8380/SCRAPSYSTEM/"


    val BASE_IMAGE_URL = "${BuildConfig.BASE_URL}SCRAPSYSTEM/api/getimage"

    var CLIENT_USERNAME = ""
    var CLIENT_PASSWORD = ""
    /*const val KEY_USERNAME = "username"
    const val KEY_PASSWORD = "password"*/
    const val KEY_GRANT_TYPE = "grant_type"
    const val KEY_CREATE_TICKET = "can_create_ticket"
    const val KEY_ACCESS_TOKEN = "access_token"
    const val KEY_REFRESH_TOKEN = "refresh_token"
    const val KEY_CAMERA = "camera"
    const val KEY_UPLOAD = "upload"
    const val KEY_FOLDER = "folder"
    const val KEY_TICKET_ID = "ticket_id"

    const val CAMERA_PERMISSION_REQ_CODE = 100
    const val PICTURE_CAPTURE_REQ_CODE = 1
    const val PICK_MULTIPLE_IMAGE_REQ_CODE = 2

    val appPermission = arrayOf(Manifest.permission.CAMERA, Manifest.permission.READ_EXTERNAL_STORAGE)
    const val AUTHORITY = "com.imageuploader.sample.fileprovider"
    const val DATE_FORMAT = "yyyyMMddHHmmss"
}