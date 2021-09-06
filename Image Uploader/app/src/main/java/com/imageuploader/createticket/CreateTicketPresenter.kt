package com.imageuploader.createticket

import android.util.Log
import com.imageuploader.R
import com.imageuploader.base.BasePresenter
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.data.model.*
import com.imageuploader.data.service.AuthApiService
import com.imageuploader.model.response.ResponseModel
import io.reactivex.observers.DisposableSingleObserver
import okhttp3.MediaType
import okhttp3.MultipartBody
import okhttp3.RequestBody
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.File
import java.lang.Exception
import java.util.*

class CreateTicketPresenter(val view: CreateTicketView) : BasePresenter<CreateTicketView>(view) {

    var ticketContent: TicketDetailsForm? = null

    fun fetchPartNameList(data: RequestBody) {
        //val auth =  AppPreference.getStringValue(AppConstants.KEY_ACCESS_TOKEN)!!
        subscribe(AuthApiService.createTicketService().fetchPartNumberList(data), object :
            DisposableSingleObserver<List<PartNumberResponse>>() {
            override fun onSuccess(value: List<PartNumberResponse>?) {
                Log.d("TAG", "Success")
                value?.let {
                    getView()?.updatePartNumber(it)
                }
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG", "Failed")
            }
        })

    }

    fun validatePart(validationModel: ValidationModel) {
        subscribe(AuthApiService.createTicketService().validateExpiryPart(validationModel), object :
            DisposableSingleObserver<List<ValidationResponse>>() {
            override fun onSuccess(value: List<ValidationResponse>?) {
                Log.d("TAG", "Success")
                value?.let {

                    if (it.isNotEmpty()) {
                        getView()?.updateValidationResposne(it[0])
                    }
                }
            }

            override fun onError(e: Throwable?) {
                Log.d("TAG", "Failed")
            }
        })
    }

    fun getSupplierName(supplierCode: String?, description: RequestBody) {

        subscribe(
            AuthApiService.createTicketService().fetchSupplierNameByCode(description),
            object : DisposableSingleObserver<SupplierNameResponse>() {
                override fun onSuccess(value: SupplierNameResponse?) {
                    Log.d("TAG", "Success")
                    getView()?.updateSupplierName(value)
                }

                override fun onError(e: Throwable?) {
                    Log.d("TAG", "Failed")
                }

            })

    }

    fun createTicketInit() {
        getView()?.showProgressbar()
        subscribe(
            AuthApiService.createTicketService().createTicketInit(),
            object : DisposableSingleObserver<TicketDetailsForm>() {
                override fun onSuccess(value: TicketDetailsForm?) {
                    Log.d("TAG", "Success")

                    value?.let {
                        ticketContent = it

                        val costCode = it.content?.costCodeMap?.map {
                            it.value
                        }

                        val changeCode = it.content?.chargeCodeMap?.map {
                            it.value
                        }

                        //changeCode?.toMutableList()?.add(0,"")

                        getView()?.updateChangeCode(changeCode)
                        getView()?.updateCostCode(costCode)
                        getView()?.updateSection()
                        getView()?.hideProgressbar()
                    }

                }

                override fun onError(e: Throwable?) {
                    Log.d("TAG", "Failed")

                    getView()?.hideProgressbar()
                }

            })
    }

    fun fetchReportingDetailsForGroup(description: RequestBody) {
        subscribe(
            AuthApiService.createTicketService().fetchReportingDetailsForGroup(description),
            object : DisposableSingleObserver<ReportingInformationModel>() {
                override fun onSuccess(value: ReportingInformationModel?) {
                    Log.d("TAG", "Success")
                    value?.let {
                        getView()?.updateReportDetails(it)

                    }

                }

                override fun onError(e: Throwable?) {
                    Log.d("TAG", "Failed")
                }

            })
    }

    fun createTicket(content: TicketDetails,processStat: String?, changeCode: Int) {
        getView()?.showProgressbar()
        subscribe(
            AuthApiService.createTicketService().createTicket(content),
            object : DisposableSingleObserver<TicketDetailsForm>() {
                override fun onSuccess(value: TicketDetailsForm?) {
                    Log.d("TAG", "Success")
                    // val ticketDetailsForm = TicketDetailsForm()
                    //ticketDetailsForm.content = value

                    value?.let {

                        it.content?.takeIf {
                            !it.errorMessages.isNullOrEmpty()
                        }?.let {
                            getView().showMessage(it.errorMessages!![0])
                        } ?: kotlin.run {

                            when(processStat) {

                                null -> {
                                    getView().createTicketSuccess(value)
                                }

                                "SQA" -> {
                                    value.content?.tagNumber?.let { it1 -> transferToSqa(it1) }
                                }

                                else -> {
                                    value.content?.tagNumber?.let { it1 -> acceptChanges(it1, changeCode) }

                                }

                            }


                        }
                    }

                }


                override fun onError(e: Throwable?) {
                    Log.d("TAG", "Failed")
                }

            })
    }

    private fun acceptChanges(tagNumber: String, changeCode: Int) {
        getView().showProgressbar()
        subscribe(
            AuthApiService.createTicketService().acceptChargeOnCreate(changeCode,tagNumber),
            object : DisposableSingleObserver<TicketDetailsForm>() {
                override fun onSuccess(value: TicketDetailsForm?) {
                    getView().hideProgressbar()
                    getView().onProcessTicketCompleted()
                }

                override fun onError(e: Throwable?) {
                    getView().hideProgressbar()

                }

            })
    }

    private fun transferToSqa(tagNumber: String) {
        getView().showProgressbar()
        subscribe(
            AuthApiService.createTicketService().transferTicketToSQAOnCreate(tagNumber),
            object : DisposableSingleObserver<TicketDetailsForm>() {
                override fun onSuccess(value: TicketDetailsForm?) {
                   getView().hideProgressbar()
                    getView().onProcessTicketCompleted()
                }

                override fun onError(e: Throwable?) {
                    getView().hideProgressbar()

                }

            })
    }

    fun onImageUpload(
        pictureFilePath: String?,
        selectedPaths: MutableList<String>,
        request: String,
        isSelected: Boolean
    ) {
        if (pictureFilePath == null && selectedPaths.size == 0) {
            getView().showMessage(R.string.please_upload_files)
            return
        }

        getView().showProgressbar()
        val parts = mutableListOf<MultipartBody.Part>()
        if (isSelected) {
            selectedPaths.forEach {
                try {

                    var str = it.replace("file:///","")
                    str = str.replace("file:/","")
                    val file = File(str)

                    val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
                    val part = MultipartBody.Part.createFormData("files", file.name, fileReqBody)
                    parts.add(part)
                } catch (e: Exception) {
                    Log.d("Err",e.message)
                }

            }
        } else {
            val file = File(pictureFilePath)
            val fileReqBody = RequestBody.create(MediaType.parse("image/*"), file)
            val part = MultipartBody.Part.createFormData("files", file.name, fileReqBody)
            parts.add(part)

        }
        // uploadToServer(parts)


        val uploadAPIs = AuthApiService.createTicketService()
        val description = RequestBody.create(MediaType.parse("text/plain"), request)

        subscribe(
            uploadAPIs.uploadTicketImages(parts.toTypedArray(), description),
            object : DisposableSingleObserver<TicketDetailsForm>() {
                override fun onSuccess(value: TicketDetailsForm?) {
                    getView().onFileUploadSuccess()
                }

                override fun onError(e: Throwable?) {

                    getView().onFileUploadFailed()
                }
            })

    }

    fun deleteImages(selectedPaths: MutableList<String>) {

        subscribe(
            AuthApiService.createTicketService().deleteImage(selectedPaths),
            object : DisposableSingleObserver<Status>() {
                override fun onSuccess(value: Status?) {

                    getView().deleteImageSuccess()
                }

                override fun onError(e: Throwable?) {
                    getView().onFileUploadFailed()

                }
            })

    }




}