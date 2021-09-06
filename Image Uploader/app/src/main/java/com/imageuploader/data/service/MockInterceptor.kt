package com.imageuploader.data.service

import com.imageuploader.BuildConfig
import okhttp3.*

class MockInterceptor : Interceptor {

    override fun intercept(chain: Interceptor.Chain): Response {
        if (BuildConfig.DEBUG) {
            val uri = chain.request().url().uri().toString()
            val responseString = when {
                uri.endsWith("token") -> loginRes
                uri.endsWith("getimagesbycmt ") -> getImageByCmt
                else -> ""
            }

            return chain.proceed(chain.request())
                .newBuilder()
                .code(200)
                .protocol(Protocol.HTTP_2)
                .message(responseString)
                .body(
                    ResponseBody.create(
                        MediaType.parse("application/json"),
                    responseString.toByteArray()))
                .addHeader("content-type", "application/json")
                .build()
        } else {
            //just to be on safe side.
            throw IllegalAccessError("MockInterceptor is only meant for Testing Purposes and " +
                    "bound to be used only with DEBUG mode")
        }
    }

}

const val loginRes = """{"access_token":"2f1915f0-f166-4e3c-8848-9f40103e0c0b","token_type":"bearer","refresh_token":"fd018e0b-0bd9-4fc0-8f8b-758bb8e90e67","scope":"read write trust"}"""
const val getImageByCmt = """"{"2019/08/22 11:12--fgfgfg":["201908221112201"],"2019/08/22 10:37--Pick 4 Files":["2019082210372001","2019082210372002","2019082210372003","2019082210372004"],"2019/08/22 10:36--pick 1 file":["2019082210364401"],"2019/08/22 10:24--Img captured":["201908221024201"],"2019/08/22 10:00--test":["201908221005601"],"2019/08/22 09:52--Test":["201908229521001"],"2019/08/22 09:50--Tester":["20190822950401"],"2019/08/19 09:38--test6":["201908199382501","201908199382502","201908199382503","201908199382504","201908199382505","201908199382506"]}"""