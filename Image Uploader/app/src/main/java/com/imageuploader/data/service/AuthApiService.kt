package com.imageuploader.data.service

import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.model.*
import com.imageuploader.model.request.RequestModel
import com.imageuploader.model.response.ResponseModel
import io.reactivex.Single
import okhttp3.*
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import java.util.concurrent.TimeUnit

interface AuthApiService {

    @POST("api/login")
    fun getLogin() : Single<LoginResponse>

    @GET("api/getimages")
    fun getAllImages(): Single<List<RequestModel.Gallery>>

    @get:GET("api/getimage")
    val imagesIndividual: Call<ResponseModel.GalleryResponse>

    @GET("api/getimagesbycmt")
    fun getAllImagesByCmt(): Single<Map<String, List<String>>>

    @Multipart
    @POST("api/uploadScrapImageFiles")
    fun uploadImages(@Part files: Array<MultipartBody.Part>, @Part("cmt") requestBody: RequestBody): Call<ResponseModel.UploadResponse>

    @POST("api/fetchPartNumberList")
    fun fetchPartNumberList(@Body karbanNumber: RequestBody) : Single<List<PartNumberResponse>>

    @POST("api/validateExpiryPart")
    fun validateExpiryPart(@Body validationModel: ValidationModel) : Single<List<ValidationResponse>>

    @POST("api/fetchSupplierNameByCode")
    fun fetchSupplierNameByCode(@Body supplierCode: RequestBody) : Single<SupplierNameResponse>

    @GET("api/createTicketInit")
    fun createTicketInit(): Single<TicketDetailsForm>

    @POST("api/fetchReportingDetailsForGroup")
    fun fetchReportingDetailsForGroup(@Body supplierCode: RequestBody) : Single<ReportingInformationModel>

    @GET("api/fetchTicketsByCriteria")
    fun fetchTicketsByCriteria(@Query("criteria")  criteria: String) : Single<TicketResponse>

    @POST("api/createTicket")
    fun createTicket(@Body content: TicketDetails) : Single<TicketDetailsForm>

    @POST("api/fetchTicketDetails")
    fun fetchTicketDetails(@Body ticketId: RequestBody) : Single<TicketDetailsForm>

    @GET("api/fetchTicketsByCriteriaView?criteria=assignedByYou")
    fun fetchTicketsByCriteriaView() : Single<TicketCountResponse>

    @Multipart
    @POST("api/uploadFiles")
    fun uploadTicketImages(@Part files: Array<MultipartBody.Part>, @Part("ticket") requestBody: RequestBody) : Single<TicketDetailsForm>


    @POST("api/deleteimages")
    fun deleteImage(@Body listOfImages:List<String>) : Single<Status>


    @POST("api/transferTicketToSQAOnCreate")
    fun transferTicketToSQAOnCreate(@Query("tagNumber") tagNumber : String) : Single<TicketDetailsForm>

    @POST("api/acceptChargeOnCreate")
    fun acceptChargeOnCreate(@Query("chargeCode") chargeCode: Int, @Query("tagNumber") tagNumber: String) : Single<TicketDetailsForm>

    companion object Factory {

        //val authorization2= getAuthorizationHeader2()

       /* fun create(): AuthApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY
            val client = OkHttpClient.Builder().addInterceptor(interceptor).build()
            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(client).baseUrl(BuildConfig.BASE_URL)
                .build()
            return retrofit.create(AuthApiService::class.java);
        }*/

        fun createTicketService(): AuthApiService {
            val interceptor = HttpLoggingInterceptor()
            interceptor.level = HttpLoggingInterceptor.Level.BODY

            val basicAuthInterceptor = Interceptor { chain ->
                val newRequest = chain.request().newBuilder()
                    .addHeader("Authorization", Credentials.basic(AppConstants.CLIENT_USERNAME, AppConstants.CLIENT_PASSWORD)).build()
                chain.proceed(newRequest)
            }


            val client = OkHttpClient.Builder().addInterceptor(interceptor)
                .readTimeout(15, TimeUnit.MILLISECONDS)
                .addInterceptor(basicAuthInterceptor).build()
            val retrofit = Retrofit.Builder()
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create()).client(client).baseUrl(AppConstants.BASE_AUTH_TICKET)
                .build()
            return retrofit.create(AuthApiService::class.java);
        }

        /*fun getAuthorizationHeader2(): String {
            return "Basic " + Base64.encodeToString("${AppPreference.getStringValue(AppConstants.KEY_USERNAME)}:${AppPreference.getStringValue(AppConstants.KEY_PASSWORD)}".toByteArray(), Base64.NO_WRAP)
        }*/
    }
}
