package com.imageuploader.createticket

import android.Manifest
import android.app.Activity
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.RadioButton
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.google.gson.Gson
import com.imageuploader.R
import com.imageuploader.api.ImageFilePath
import com.imageuploader.base.BaseActivity
import com.imageuploader.base.ErrorHandlerView
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.data.model.*
import com.imageuploader.features.galleryview.GalleryActivity
import com.imageuploader.features.galleryview.GalleryImageAdapter
import com.imageuploader.features.imageupload.CameraFilterSheetFragment
import com.imageuploader.helper.MyCustomAdapter
import com.imageuploader.interfaces.ListClickListener
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.utils.Utils
import com.imageuploader.widgets.MyAlertDialog
import kotlinx.android.synthetic.main.activity_create_ticket.*
import kotlinx.android.synthetic.main.activity_create_ticket.progressBar
import kotlinx.android.synthetic.main.activity_create_ticket.toolbar
import okhttp3.Credentials
import okhttp3.MediaType
import okhttp3.RequestBody
import java.io.File
import java.io.IOException
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class CreateTicketActivity : BaseActivity(), CreateTicketView {
    override fun onProcessTicketCompleted() {
        finish()
        overridePendingTransitionExit()
    }
    var mBlockCompletion = true;

    private var supplierCode = ""
    private var changeCode = 0
    lateinit var itemListDataAdapter: GalleryImageAdapter
    private var isFromGallery = false
    private var selectedUri = mutableListOf<String>()
    override fun updateValidationResposne(validationResponse: ValidationResponse) {

        if (validationResponse.expiredFlag) {
            val myAlertDialog = MyAlertDialog(this@CreateTicketActivity)
            myAlertDialog.setAlertIcon(getDrawable(R.drawable.ic_info_black_24dp)!!)
            myAlertDialog.setAlertDesciption(
                "Part Number entered was last purchased on.2016-05-05\n" +
                        "Are you sure the correct part number has been entered? \n\n ${validationResponse.partNumber}"
            )
            myAlertDialog.setRightButtonText("Ok")
            myAlertDialog.setLeftButtonText("Cancel")
            myAlertDialog.setOnActionListener(object :
                MyAlertDialog.DialogActionListener {
                override fun onAction(view: View) {

                    when (view.id) {
                        R.id.btn_right -> {
                            myAlertDialog.dismiss()
                            val description =
                                RequestBody.create(MediaType.parse("text/plain"), supplierCode)
                            presenter?.getSupplierName(supplierCode, description)
                        }

                        R.id.btn_left -> {
                            text_part_number_or_karban_number.setText("")
                            myAlertDialog.dismiss()
                        }
                    }

                }
            })
            myAlertDialog.show()

        } else {
            val description = RequestBody.create(MediaType.parse("text/plain"), supplierCode)
            presenter?.getSupplierName(supplierCode, description)
        }

    }

    private val MY_CAMERA_PERMISSION_CODE = 100
    private var pictureFilePath: String? = null
    private var selectedPaths = mutableListOf<String>()
    private lateinit var reportingInformationModel: ReportingInformationModel
    private lateinit var parts: List<PartNumberResponse>
    private var sectionItems: Set<String>? = null
    private var defCause: List<String>? = arrayListOf()
    private var typeOfDefect: List<String>? = arrayListOf()
    val bottomSheetFragment = CameraFilterSheetFragment(true)

    override fun updatePartNumber(parts: List<PartNumberResponse>) {
        this.parts = parts
        val partInfoList = parts.map {
            PartInfo(it.partName!!,it.partNumber)
        }
        val partNumber = parts.map {
            it.partNumber
        }
        partNumberAdapter = MyCustomAdapter(this, R.layout.layout_partnumber, ArrayList(partInfoList))
        text_part_number_or_karban_number.threshold = 3
        text_part_number_or_karban_number.setAdapter(partNumberAdapter)
        text_part_number_or_karban_number.showDropDown()


        // partNumberAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, partNames)


    }

    private var presenter: CreateTicketPresenter? = null
    lateinit var partNumberAdapter: MyCustomAdapter
    lateinit var reportAdapter: ArrayAdapter<String>
    lateinit var chargeCodeAdapter: ArrayAdapter<String>
    lateinit var costCodeAdapter: ArrayAdapter<String>
    lateinit var sectionAdapter: ArrayAdapter<String>
    lateinit var defectCauseAdapter: ArrayAdapter<String>
    lateinit var typeOfDefectAdapter: ArrayAdapter<String>
    private var isSelected = false;


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_create_ticket)
        itemListDataAdapter = GalleryImageAdapter(this)
        presenter = CreateTicketPresenter(this)

        toolbar.setToolbarClickListener(object : ToolbarClickListener {
            override fun onToolbarClick(view: View) {
                when (view.id) {

                    R.id.img_left_icon -> {
                        finish()
                        overridePendingTransitionExit()
                    }
                }
            }
        })

        text_part_number_or_karban_number.setOnItemClickListener { parent, view, position, id ->
            val parts = parts[position]
            text_part_number_or_karban_number.setText(parts.partNumber)
            ed_partname.setText(parts.partName)
            ed_supplier_code.setText(parts.supplierCode)
            ed_karban_number.setText(parts.kanbanNumber)

            parts.supplierCode?.let {
                val validationModel = ValidationModel(parts.partNumber, it)
                supplierCode = it
                presenter?.validatePart(validationModel)

            }
        }

        ed_reporting_badge_number.setOnItemClickListener { parent, view, position, id ->
            mBlockCompletion = true;
            ed_reporting_associate.setText(reportingInformationModel.associateName)
            mBlockCompletion = false;

        }

        text_part_number_or_karban_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                if (mBlockCompletion) return;
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(!text_part_number_or_karban_number.isPerformingCompletion) {
                    if (s.toString().length > 2) {
                        val description = RequestBody.create(
                            MediaType.parse("text/plain"),
                            s.toString().toUpperCase()
                        )
                        presenter?.fetchPartNameList(description)
                    }
                }

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        ed_reporting_badge_number.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {

                if(!ed_reporting_badge_number.isPerformingCompletion) {
                    if (s.toString().length > 2) {
                        val description =
                            RequestBody.create(MediaType.parse("text/plain"), s.toString())
                        presenter?.fetchReportingDetailsForGroup(description)
                    }
                }

            }

            override fun afterTextChanged(s: Editable) {

            }
        })

        spinner_section.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                sectionItems?.let {
                    val section = it.elementAt(position)
                    defCause =
                        presenter?.ticketContent?.content?.sectionWithDefectDetModels?.filter {
                            it.sectionDescription == section
                        }?.first()?.defectCauses?.map { it.defectCauseDesc!! }

                    defectCauseAdapter =
                        ArrayAdapter<String>(
                            this@CreateTicketActivity,
                            android.R.layout.select_dialog_item,
                            defCause!!
                        )

                    spinner_defect_cause.adapter = defectCauseAdapter
                }
            }
        }

        /*spinner_charge_code.setOnClickListener {

            val changeCode = presenter?.ticketContent?.content?.chargeCodeMap?.map {
                it.value
            }

            updateChangeCode(changeCode)

        }*/



        defectCauseAdapter =
            ArrayAdapter<String>(
                this@CreateTicketActivity,
                android.R.layout.select_dialog_item,
                defCause!!
            )
        spinner_defect_cause.adapter = defectCauseAdapter

        spinner_defect_cause.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {

                defCause?.let {
                    val cause = it[position]
                    val section = spinner_section.selectedItem.toString()
                    typeOfDefect =
                        presenter?.ticketContent?.content?.sectionWithDefectDetModels?.filter {
                            it.sectionDescription == section
                        }?.first()?.defectCauses?.filter { it.defectCauseDesc == cause }?.first()
                            ?.defectTypes?.map { it.defectTypeDesc.toString() }

                    typeOfDefectAdapter = ArrayAdapter<String>(
                        this@CreateTicketActivity,
                        android.R.layout.select_dialog_item,
                        typeOfDefect!!
                    )
                    spinner_type_of_defect.adapter = typeOfDefectAdapter
                }
            }
        }
        typeOfDefectAdapter =
            ArrayAdapter<String>(
                this@CreateTicketActivity,
                android.R.layout.select_dialog_item,
                typeOfDefect!!
            )
        spinner_type_of_defect.adapter = typeOfDefectAdapter

        button_create_ticket.setOnClickListener {
            validateAndCreateTicket(null, 0)
        }

        button_process_ticket.setOnClickListener {


            val selected = spinner_charge_code.selectedItem

            selected?.let {
                it.toString().let {
                    validateAndCreateTicket(it, spinner_charge_code.selectedItemPosition)
                }
            }

        }


        img_attach_photo.setOnClickListener {


            bottomSheetFragment.setOnItemClickListener(object : ListClickListener<String> {
                override fun onClick(view: View, data: String) {
                    bottomSheetFragment.dismiss()

                    when (data) {

                        AppConstants.KEY_UPLOAD -> {
                            val intent1 =
                                Intent(this@CreateTicketActivity, GalleryActivity::class.java)
                            intent1.putExtra("isChooser", true)
                            startActivityForResult(intent1, 200)
                        }

                        AppConstants.KEY_CAMERA -> {
                            // image_viewer.visible(true)
                            // recyclerview_selected.visible(false)
                            isSelected = false
                            if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
                                sendTakePictureIntent()
                            }
                        }

                        AppConstants.KEY_FOLDER -> {
                            isSelected = true
                            val intent = Intent()
                            intent.type = "image/*";
                            intent.putExtra(Intent.EXTRA_ALLOW_MULTIPLE, true);
                            intent.action = Intent.ACTION_GET_CONTENT;
                            startActivityForResult(
                                Intent.createChooser(intent, getString(R.string.select_picture)),
                                AppConstants.PICK_MULTIPLE_IMAGE_REQ_CODE
                            );
                        }
                        else -> {

                        }
                    }
                }
            })
            bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag);
        }


        val imagesExtra = intent.getStringArrayListExtra("images")
        imagesExtra?.let {
            val paths = mutableListOf<String>()
            val urls = intent.getStringArrayListExtra("urls")
            selectedUri = urls

            it.forEach {
                paths.add(ImageFilePath.getPath(this, Uri.parse(it))!!)
            }

            paths.let {
                isFromGallery = true
                isSelected = true
                recyclerview_attach_photos.setHasFixedSize(true)
                recyclerview_attach_photos.layoutManager =
                    androidx.recyclerview.widget.GridLayoutManager(this, 4)
                recyclerview_attach_photos.setAdapter(itemListDataAdapter)
                itemListDataAdapter.addSelectedPhotos(paths, true)
                selectedPaths = paths
                itemListDataAdapter.setHeros(mutableListOf(), true)
            }

        }

        presenter?.createTicketInit()
    }

    val changeListener = object : AdapterView.OnItemSelectedListener {
        override fun onNothingSelected(parent: AdapterView<*>?) {
        }

        override fun onItemSelected(
            parent: AdapterView<*>?,
            view: View?,
            position: Int,
            id: Long
        ) {

            val selectedItem = spinner_charge_code.selectedItem
            var selectedCode = 0
            try {
                selectedCode = presenter?.ticketContent?.content?.chargeCodeMap?.filter {
                    it.value == selectedItem.toString()
                }?.keys?.first()!!

            } catch (e: Exception) {

            }

            selectedCode?.let {
                changeCode = selectedCode
            }
            selectedItem?.let {
                it.toString().let {

                    if (it == "SQA") {
                        button_process_ticket.text = "Transfer To SQA"
                    } else {
                        button_process_ticket.text = "Process Ticket"
                    }

                }
            }
        }
    }



    private fun validateAndCreateTicket(processStat: String?, code: Int) {

        val content = presenter?.ticketContent?.content

        val partNumber = text_part_number_or_karban_number.text.toString()

        if (partNumber.isEmpty()) {
            setError(text_part_number_or_karban_number, "Part Number is Empty")
            return
        }
        content?.partNumber = partNumber

        val partName = ed_partname.text.toString()
        if (partName.isEmpty()) {
            setError(ed_partname, "Part Number is Empty")
            return
        }
        content?.partName = partName

        val supplierCode = ed_supplier_code.text.toString()
        if (supplierCode.isEmpty()) {
            setError(ed_partname, "Supplier Code is Empty")
            return
        }
        content?.supplierCode = supplierCode

        val supplierName = ed_supplier_name.text.toString()
        if (supplierName.isEmpty()) {
            setError(ed_supplier_name, "Supplier Name is Empty")
            return
        }
        content?.supplierName = supplierName

        val reportingBadge = ed_reporting_badge_number.text.toString()
        if (reportingBadge.isEmpty()) {
            setError(ed_reporting_badge_number, "Reporting Badge Number is Empty")
            return
        }
        content?.reportingBadgeNumber = reportingBadge

        val reportingAssociate = ed_reporting_associate.text.toString()

        if (reportingAssociate.isEmpty()) {
            setError(ed_reporting_associate, "Reporting Associate is Empty")
            return
        }
        content?.reportingAssociate = reportingAssociate

        val costCode = spinner_cost_code.selectedItem.toString()
        if (costCode.isEmpty()) {
            setError(spinner_cost_code, "Cost Code is Empty")
            return
        }
        content?.basicCostCode = content?.costCodeMap?.entries?.first {
            it.value == costCode
        }?.key

        val quality = ed_quatity.text.toString()
        if (quality.isEmpty()) {
            setError(ed_quatity, "Quality is Empty")
            return
        }
        content?.quantity = quality.toLong()
        content?.rejectCode = ed_reject_code.text.toString()
        content?.sequenceNumber = ed_sequence_number.text.toString()

        val section = spinner_section.selectedItem.toString()

        if (section.isEmpty()) {
            setError(spinner_section, "Section is Empty")
            return
        }
        val sectionID =
            content?.sectionWithDefectDetModels?.filter { it.sectionDescription == section }
                ?.first()?.sectionID

        if (sectionID == null) {
            setError(spinner_section, "Invalid Section")
            return
        }

        //content.currentSectionID = sectionID.toLong()
        content.defectSectionID = sectionID.toString()

        if (spinner_defect_cause.selectedItem == null) {
            setError(spinner_defect_cause, "Defect Cause is Empty")
            return
        }

        val defectCause = spinner_defect_cause.selectedItem.toString()
        if (defectCause.isEmpty()) {
            setError(spinner_defect_cause, "Defect Cause is Empty")
            return
        }
        val defectCauseId =
            content.sectionWithDefectDetModels?.filter { it.sectionDescription == section }?.first()
                ?.defectCauses?.filter { it.defectCauseDesc == defectCause }?.first()?.defectCauseID

        if (defectCauseId == null) {
            setError(spinner_section, "Invalid Section")
            return
        }
        content.chargeCode = changeCode

        content.defectCauseID = defectCauseId.toString()

        if (spinner_type_of_defect.selectedItem == null) {
            setError(spinner_defect_cause, "Type of Defect is Empty")
            return
        }

        val typeOfDefect = spinner_type_of_defect.selectedItem.toString()
        if (typeOfDefect.isEmpty()) {
            setError(spinner_defect_cause, "Type of Defect is Empty")
            return
        }


        val typeOfDefectId =
            content.sectionWithDefectDetModels?.filter { it.sectionDescription == section }?.first()
                ?.defectCauses?.filter { it.defectCauseDesc == defectCause }?.first()
                ?.defectTypes?.filter {
                it.defectTypeDesc == typeOfDefect
            }?.first()?.defectTypeID

        if (typeOfDefectId == null) {
            setError(spinner_defect_cause, "Type of Defect is Empty")
            return
        }

        content.defectTypeID = typeOfDefectId.toString()
        content.defectDesc = ed_defect_description.text.toString()

        val selectedLine = findViewById<RadioButton>(radio_line.checkedRadioButtonId)

        if (selectedLine == null) {
            setError(radio_line_a, "Line not selected")
            return
        }

        content.lineCode = selectedLine.tag.toString()

        /*content.sectionWithDefectDetModels?.forEach {

            it.sectionID = null

        }*/

        // content.sectionWithDefectDetModels.get(0).defectCauses.get(0)
        content.sectionID = null
        presenter?.createTicket(content, processStat, code)

    }


    private fun setError(view: View, message: String) {
        Snackbar.make(view, message, Snackbar.LENGTH_SHORT).show()
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == MY_CAMERA_PERMISSION_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                sendTakePictureIntent()
            } else {
                Toast.makeText(this, "camera permission denied", Toast.LENGTH_LONG).show()
            }

        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        if (requestCode == 200) {
            val stringArrayListExtra = data?.getStringArrayListExtra("url")
            Log.d("stringArrayListExtra", "$stringArrayListExtra")
            stringArrayListExtra?.let {
                isFromGallery = true
                selectedUri = it
                val arrList = arrayListOf<String>()
                // arrList.add(it)

                var count = 0
                while (count < it.size) {
                    val urlg = GlideUrl(
                        "${it[count]}", LazyHeaders.Builder()
                            .addHeader(
                                "Authorization", Credentials.basic(
                                    AppPreference.getStringValue(AppConstants.CLIENT_USERNAME),
                                    AppPreference.getStringValue(
                                        AppConstants.CLIENT_PASSWORD
                                    )
                                )
                            )
                            .build()
                    )
                    Glide.with(this@CreateTicketActivity)
                        .asBitmap()
                        .load(urlg)
                        .into(object : CustomTarget<Bitmap>() {
                            override fun onResourceReady(
                                resource: Bitmap,
                                transition: Transition<in Bitmap>?
                            ) {
                                //imageView.setImageBitmap(resource)


                                val localBitmapUri =
                                    Utils.getLocalBitmapUri(resource, this@CreateTicketActivity)

                                localBitmapUri?.let {
                                    arrList.add(it.toString())
                                }


                                if (arrList.size == it.size) {

                                    isSelected = true
                                    recyclerview_attach_photos.setHasFixedSize(true)
                                    recyclerview_attach_photos.layoutManager =
                                        androidx.recyclerview.widget.GridLayoutManager(
                                            this@CreateTicketActivity,
                                            4
                                        )
                                    recyclerview_attach_photos.setAdapter(itemListDataAdapter)
                                    itemListDataAdapter.addSelectedPhotos(arrList, true)
                                    selectedPaths = arrList
                                    itemListDataAdapter.setHeros(mutableListOf(), true)
                                }


                            }

                            override fun onLoadCleared(placeholder: Drawable?) {
                                // this is called when imageView is cleared on lifecycle call or for
                                // some other reason.
                                // if you are referencing the bitmap somewhere else too other than this imageView
                                // clear it here as you can no longer have the bitmap
                            }
                        })
                    count += 1
                }

            }

        }

        if (requestCode == AppConstants.PICTURE_CAPTURE_REQ_CODE && resultCode == Activity.RESULT_OK) {
            val imgFile = File(pictureFilePath)
            if (imgFile.exists()) {
                selectedPaths.add(pictureFilePath!!)
                // image_viewer.setImageURI(Uri.fromFile(imgFile))
            }
            if (selectedPaths.isNotEmpty()) {
                recyclerview_attach_photos.layoutManager =
                    androidx.recyclerview.widget.GridLayoutManager(this, 4)
                recyclerview_attach_photos.setAdapter(itemListDataAdapter)
                itemListDataAdapter?.setHeros(selectedPaths, true)
            }
        }


        if (requestCode == AppConstants.PICK_MULTIPLE_IMAGE_REQ_CODE) {

            var selectedPaths = mutableListOf<String>()
            if (resultCode == RESULT_OK) {
                if (data == null) {
                    // something is wrong
                }

                if (data?.data != null) {
                    val mImageUri = data.data;
                    val filePath = ImageFilePath.getPath(this, mImageUri!!);
                    filePath?.let {
                        selectedPaths.add(it)
                    }
                    if (selectedPaths.isNotEmpty()) {
                        recyclerview_attach_photos.layoutManager =
                            androidx.recyclerview.widget.GridLayoutManager(this, 4)
                        itemListDataAdapter.setHeros(selectedPaths, true)
                        recyclerview_attach_photos.adapter = itemListDataAdapter
                    }
                } else {
                    val clipData = data?.clipData
                    if (clipData != null) { // handle multiple photo
                        selectedPaths = mutableListOf()
                        for (i in 0 until clipData.itemCount) {
                            val uri = clipData.getItemAt(i).uri
                            val filePath = ImageFilePath.getPath(this, uri);
                            filePath?.let {
                                selectedPaths.add(it)
                                //val imageUri = Uri.fromFile(it);
                            }
                            //uploadToServer(filePath)
                            //importPhoto(uri)
                        }
                    }
                    if (selectedPaths.isNotEmpty()) {
                        recyclerview_attach_photos.layoutManager =
                            androidx.recyclerview.widget.GridLayoutManager(this, 4)
                        recyclerview_attach_photos.setAdapter(itemListDataAdapter)
                        itemListDataAdapter?.setHeros(selectedPaths, true)
                    }
                }


                //  btn_upload.text = "${getString(R.string.upload_image)}(${selectedPaths.size})"
            }
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransitionExit()
    }

    override fun updateChangeCode(changeCode: List<String>?) {

        changeCode?.let {
            val a = arrayListOf<String>()
            a.add("")
            a.addAll(it)
            chargeCodeAdapter = ArrayAdapter<String>(this, android.R.layout.select_dialog_item, a)
            spinner_charge_code.adapter = chargeCodeAdapter
        }

        spinner_charge_code.onItemSelectedListener = changeListener

    }

    override fun createTicket() {

    }

    override fun showMessage(message: Int) {
        Snackbar.make(img_attach_photo, getString(message), Snackbar.LENGTH_SHORT).show()
    }

    override fun showMessage(message: String) {
        hideProgressbar()
        Snackbar.make(img_attach_photo, message, Snackbar.LENGTH_SHORT).show()
    }

    private fun getPictureFilePath(): File {
        val timeStamp = SimpleDateFormat("yyyyMMddHHmmss").format(Date())
        val pictureFile = "subaru_$timeStamp"
        val storageDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val image = File.createTempFile(pictureFile, ".jpg", storageDir)
        pictureFilePath = image.absolutePath
        return image
    }

    override fun onFileUploadSuccess() {

        var deleteImage = mutableListOf<String>()

        selectedPaths.forEach {

            var str = it.replace("file:///", "")
            str = str.replace("file:/", "")
            val path = File(str)




            if (path.exists()) {
                val status = path.delete()
                Log.d("delete Status= ", "$it = $status")
            }
        }

        var images = mutableListOf<String>()
        selectedUri.forEach {
            var j = it.split("/")
            var k = j[j.size - 1]
            images.add(k)
        }

        if (isFromGallery) {
            presenter?.deleteImages(images)
        }






        deleteImageSuccess()
        //itemListDataAdapter?.setHeros(mutableListOf(), true)
    }

    override fun onFileUploadFailed() {
        hideProgressbar()
        showMessage(R.string.upload_filed)
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun updateReportDetails(reportingInformationModel: ReportingInformationModel) {

        presenter?.ticketContent?.content?.reportingBadgeNumber = reportingInformationModel.badgeID
        presenter?.ticketContent?.content?.reportingAssociate =
            reportingInformationModel.associateName
        presenter?.ticketContent?.content?.groupLeaderReviewID =
            reportingInformationModel.groupLeaderID
        presenter?.ticketContent?.content?.groupLeaderReviewName =
            reportingInformationModel.groupLeaderName
        presenter?.ticketContent?.content?.currentSectionID =
            reportingInformationModel.sectionID.toLong()


        reportAdapter =
            ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                listOf(reportingInformationModel.badgeID)
            )
        ed_reporting_badge_number.threshold = 3
        ed_reporting_badge_number.setAdapter(reportAdapter)
        ed_reporting_badge_number.showDropDown()
        this.reportingInformationModel = reportingInformationModel

    }

    override fun updateSection() {
        presenter?.ticketContent.let {
            sectionItems =
                it?.content?.sectionWithDefectDetModels?.map { it.sectionDescription.toString() }
                    ?.toSet()
            sectionAdapter = ArrayAdapter<String>(
                this,
                android.R.layout.select_dialog_item,
                sectionItems!!.toList()
            )
            spinner_section.adapter = sectionAdapter
        }
    }

    override fun updateCostCode(costCode: List<String>?) {

        hideSoftKeyboard()
        costCode?.let {
            costCodeAdapter =
                ArrayAdapter<String>(this, android.R.layout.select_dialog_item, costCode)
            spinner_cost_code.adapter = costCodeAdapter

        }
    }

    override fun updateSupplierName(value: SupplierNameResponse?) {
        ed_supplier_name.setText(value?.supplierName)
    }

    override fun createTicketSuccess(ticketDetailsForm: TicketDetailsForm) {
        hideProgressbar()
        /*Toast.makeText(this, "Ticket Created Successfully", Toast.LENGTH_SHORT).show()
        finish()
        overridePendingTransitionExit()*/


        if (pictureFilePath != null || selectedPaths.size > 0) {
            presenter?.onImageUpload(
                pictureFilePath,
                itemListDataAdapter.mHeroes,
                Gson().toJson(ticketDetailsForm.content),
                isSelected
            )
        } else {
            onFileUploadSuccess()
        }
    }

    private fun hideSoftKeyboard() {
        if (currentFocus != null) {
            val inputMethodManager =
                getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            inputMethodManager.hideSoftInputFromWindow(currentFocus!!.windowToken, 0)
        }
    }

    private fun sendTakePictureIntent() {

        if (ContextCompat.checkSelfPermission(
                this,
                Manifest.permission.CAMERA
            ) != PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(
                this,
                arrayOf(Manifest.permission.CAMERA), MY_CAMERA_PERMISSION_CODE
            )
        } else {
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true)
            if (cameraIntent.resolveActivity(packageManager) != null) {
                //startActivityForResult(cameraIntent, PICTURE_CAPTURE_REQ_CODE);

                var pictureFile: File? = null
                try {
                    pictureFile = getPictureFilePath()
                } catch (ex: IOException) {
                    Toast.makeText(
                        this,
                        "Photo file can't be created, please try again",
                        Toast.LENGTH_SHORT
                    ).show()
                    return
                }

                if (pictureFile != null) {
                    val photoURI = FileProvider.getUriForFile(
                        this,
                        "com.imageuploader.sample.fileprovider",
                        pictureFile
                    )
                    cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                    startActivityForResult(
                        cameraIntent,
                        AppConstants.PICTURE_CAPTURE_REQ_CODE
                    )
                }
            }
        }

    }


    override fun deleteImageSuccess() {
        pictureFilePath = null
        selectedPaths.clear()
        //image_viewer.setImageResource(android.R.color.transparent)
        showMessage(R.string.ticket_success)
        //et_suppliercode.setText("")
        //btn_upload.text = getString(R.string.upload_image)
        hideProgressbar()
        finish()
    }


}


interface CreateTicketView : ErrorHandlerView {

    fun createTicket()
    fun createTicketSuccess(ticketDetailsForm: TicketDetailsForm)
    fun updatePartNumber(parts: List<PartNumberResponse>)
    fun updateSupplierName(value: SupplierNameResponse?)
    fun updateCostCode(costCode: List<String>?)
    fun updateSection()
    fun updateReportDetails(reportingInformationModel: ReportingInformationModel)
    fun showProgressbar()
    fun hideProgressbar()
    fun onFileUploadSuccess()
    fun onFileUploadFailed()
    fun showMessage(message: Int)
    fun updateValidationResposne(validationResponse: ValidationResponse)
    fun deleteImageSuccess()
    fun updateChangeCode(changeCode: List<String>?)
    fun onProcessTicketCompleted()
}