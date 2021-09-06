package com.imageuploader.data.model

enum class ErrorType(code: Int, message: String) {

    ERROR_400(400, "BAD REQUEST"),
    ERROR_401(401, "UNAUTHORIZED"),
    ERROR_403(403, "You do not have permission to access this site or page. Please contact the System Administrator"),
    ERROR_404(404, "NOT FOUND"),
    ERROR_500(500, "INTERNAL SERVER ERROR"),
    ERROR_503(503, "SERVICE UNAVAILABLE");


    val mCode = code
    val mMessage = message

    fun getCode(): Int {
        return mCode
    }

    companion object {
        fun getMessage(code: Int): String {
            var message = "Unknown Error"
            val values = values()

            values.forEach {
                if (it.mCode == code) {
                    message = it.mMessage
                }
            }

            return message
        }

    }


}