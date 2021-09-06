package com.imageuploader.features.imageupload

import android.content.Intent
import android.content.pm.PackageManager
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.view.View
import android.view.WindowManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.core.content.FileProvider
import com.google.android.material.snackbar.Snackbar
import com.imageuploader.R
import com.imageuploader.api.ImageFilePath
import com.imageuploader.base.BaseActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.features.galleryview.GalleryActivity
import com.imageuploader.features.galleryview.GalleryImageAdapter
import com.imageuploader.interfaces.ListClickListener
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.activity_image_upload.*

class ImageUploadActivity : BaseActivity(), ImageUploadView {


    private var pictureFilePath: String? = null
    private var isSelected = false;
    val bottomSheetFragment = CameraFilterSheetFragment(false)
    var itemListDataAdapter: GalleryImageAdapter? = null
    private var selectedPaths = mutableListOf<String>()
    lateinit var presenter: ImageUploadPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_image_upload)
        presenter = ImageUploadPresenter(this)

        toolbar.setToolbarClickListener(object : ToolbarClickListener {
            override fun onToolbarClick(view: View) {
                when (view.id) {

                    R.id.img_right_icon -> {
                        launchActivity<GalleryActivity>()
                    }

                    R.id.img_left_icon -> {
                        finish()
                        overridePendingTransitionExit()
                    }
                }
            }
        })


        img_select_image.setOnClickListener {
            presenter.onImageSelect()
        }

        btn_upload.setOnClickListener {
            presenter.onImageUpload(
                pictureFilePath,
                selectedPaths,
                et_suppliercode.text.toString(),
                isSelected
            )
        }

    }

    private fun sendTakePictureIntent() {
        val externalFilesDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES)
        val imageUrl = presenter.getPictureFilePath(externalFilesDir)

        if(imageUrl != null){
            pictureFilePath = imageUrl.absolutePath
            val photoURI = FileProvider.getUriForFile(
                this,
                AppConstants.AUTHORITY,
                imageUrl
            )
            val cameraIntent = Intent(MediaStore.ACTION_IMAGE_CAPTURE)
            cameraIntent.putExtra(MediaStore.EXTRA_FINISH_ON_COMPLETION, true)
            if (cameraIntent.resolveActivity(packageManager) != null) {
                cameraIntent.putExtra(MediaStore.EXTRA_OUTPUT, photoURI)
                startActivityForResult(cameraIntent, AppConstants.PICTURE_CAPTURE_REQ_CODE)
            }
        } else {
            showMessage(R.string.file_path_failed)
        }

    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {

        presenter.handleActivityResult(requestCode, resultCode,data, pictureFilePath)

    }

    override fun updateSelectedImage(selectedPaths: MutableList<String>) {
        this.selectedPaths = selectedPaths
        itemListDataAdapter = GalleryImageAdapter(this)
        recyclerview_selected.adapter = itemListDataAdapter
        itemListDataAdapter?.setHeros(selectedPaths, true)
    }

    override fun getFilePathByUri(mImageUri: Uri?): String? {
        return ImageFilePath.getPath(this, mImageUri!!)
    }

    override fun updateCapturedImage(fromFile: Uri?) {
        fromFile?.let {
            image_viewer.setImageURI(it)
        }
    }

    override fun showPicSourceDialog() {
        bottomSheetFragment.setOnItemClickListener(object : ListClickListener<String> {
            override fun onClick(view: View, data: String) {
                bottomSheetFragment.dismiss()
                when (data) {

                    AppConstants.KEY_CAMERA -> {
                        presenter.onPickImageFromCamera()

                    }

                    AppConstants.KEY_FOLDER -> {
                        presenter.onPickImageFromFolder()
                    }
                    else -> {

                    }
                }
            }
        })
        bottomSheetFragment.show(supportFragmentManager, bottomSheetFragment.tag);
    }

    override fun checkPermission(): Boolean {
        val listPermissionNeeded = mutableListOf<String>()
        AppConstants.appPermission.forEach {
            if (ContextCompat.checkSelfPermission(this, it) != PackageManager.PERMISSION_GRANTED) {
                listPermissionNeeded.add(it)
            }
        }

        if (listPermissionNeeded.isNotEmpty()) {
            ActivityCompat.requestPermissions(
                this,
                listPermissionNeeded.toTypedArray(),
                AppConstants.CAMERA_PERMISSION_REQ_CODE
            )
            return false
        }

        return true
    }


    override fun showMessage(message: Int) {
        Snackbar.make(layout_image_upload, getString(message), Snackbar.LENGTH_SHORT).show()
    }

    override fun onPickImageFromCamera() {
        image_viewer.visible(true)
        recyclerview_selected.visible(false)
        isSelected = false
        if (packageManager.hasSystemFeature(PackageManager.FEATURE_CAMERA)) {
            sendTakePictureIntent()
        }
    }

    override fun onPickImageFromFolder() {
        image_viewer.visible(false)
        recyclerview_selected.visible(true)
        image_viewer.setImageResource(android.R.color.transparent);
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

    override fun onFileUploadSuccess() {
        pictureFilePath = null
        selectedPaths.clear()
        image_viewer.setImageResource(android.R.color.transparent)
        showMessage(R.string.upload_success)
        et_suppliercode.setText("")
        btn_upload.text = getString(R.string.upload_image)
        progressStatus(false)
        itemListDataAdapter?.setHeros(mutableListOf(), true)
    }

    override fun onFileUploadFailed() {
        progressStatus(false)
        showMessage(R.string.upload_filed)
    }


    override fun progressStatus(status: Boolean) {

        if (status) {
            progressBar.visible(true)
            window.setFlags(
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
            )
        } else {
            progressBar.visible(false)
            window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransitionExit()
    }

}
