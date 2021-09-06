package com.imageuploader.features.galleryview

import android.app.Activity
import android.app.ActivityOptions
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.View
import com.bumptech.glide.Glide
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.google.android.material.snackbar.Snackbar
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.createticket.CreateTicketActivity
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.features.gallerydetails.GalleryDetailsActivity
import com.imageuploader.helper.AppConstants
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.utils.Utils
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.activity_gallery.*
import okhttp3.Credentials
import java.util.*
import kotlin.collections.ArrayList

class GalleryActivity : BaseActivity(), GalleryView {

    override fun showMessage(message: String) {
        progressBar.visible(false)
        Snackbar.make(gallery_view, message, Snackbar.LENGTH_SHORT).show();
    }

    override fun loadGalleryItems(items:  Map<String, List<String>>) {
        setupGalleryAdapter(items)
    }

    private var galleryAdapter: GalleryAdapter? = null

    private var presenter: GalleryPresenter? = null

    var isChoose: Boolean  = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery)

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


        val r = intent.extras
        intent.extras?.getBoolean("isChooser",false)?.let {
            isChoose = it
        } ?: kotlin.run {
            isChoose = false
        }


            if(isChoose) {
                button_set_result.visible(true)
                button_set_result.setOnClickListener {

                    val intent = Intent()

                    var uris = mutableListOf<String>()
                    galleryAdapter?.selectedItems?.forEach { s, set ->
                        set.forEach { str ->
                            uris.add(str)
                        }
                    }
                    val  rr: ArrayList<String> =uris as ArrayList<String>
                    intent.putExtra("url",rr)
                    setResult(Activity.RESULT_OK,intent)
                    finish()
                }

            } else {
                button_set_result.visible(false)
            }




        presenter = GalleryPresenter(this)
        progressBar.visible(true)
        presenter?.loadGalleryItems()



    }
    override fun onBackPressed() {
        super.onBackPressed()
        finish()
        overridePendingTransitionExit()
    }

    private fun setupGalleryAdapter(items:  Map<String, List<String>>) {


        galleryAdapter = GalleryAdapter(this, items,isChoose)
        galleryAdapter?.setGalleryClickLisener(object : GalleryClickListener {

            override fun onCreateClick(view: View, urls: List<String>) {
                val arrList = arrayListOf<String>()

                val uris  = arrayListOf<String>()


                       // arrList.add(it)



                        var count = 0
                        while (count < urls.size) {
                            uris.add("${com.imageuploader.data.appdata.AppConstants.BASE_IMAGE_URL}/${urls[count]}")
                            val urlg = GlideUrl(
                                "${com.imageuploader.data.appdata.AppConstants.BASE_IMAGE_URL}/${urls[count]}", LazyHeaders.Builder()
                                    .addHeader("Authorization", Credentials.basic(
                                        AppPreference.getStringValue(com.imageuploader.data.appdata.AppConstants.CLIENT_USERNAME),
                                        AppPreference.getStringValue(
                                            com.imageuploader.data.appdata.AppConstants.CLIENT_PASSWORD)))
                                    .build()
                            )
                            Glide.with(this@GalleryActivity)
                                .asBitmap()
                                .load(urlg)
                                .into(object : CustomTarget<Bitmap>(){
                                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                                        //imageView.setImageBitmap(resource)


                                        val localBitmapUri =
                                            Utils.getLocalBitmapUri(resource, this@GalleryActivity)

                                        localBitmapUri?.let {
                                            arrList.add(it.toString())
                                        }



                                        if(arrList.size == urls.size) {
                                            val intent =  Intent(this@GalleryActivity, CreateTicketActivity::class.java)
                                            intent.putStringArrayListExtra("images", arrList)
                                            intent.putStringArrayListExtra("urls", uris)
                                            startActivity(intent)
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



            override fun onclick(view: View,url: String,cmd: String) {
                val options = ActivityOptions.makeSceneTransitionAnimation(
                    this@GalleryActivity, view, getString(R.string.hero_transition_name))
                val intent = Intent(this@GalleryActivity, GalleryDetailsActivity::class.java)
                intent.putExtra(AppConstants.HERO_URL, url)
                intent.putExtra(AppConstants.GALLERY_COMMENTS, cmd)
                launchActivityWithIntent(intent, options.toBundle())
            }
        })
        setupGalleryView()
        progressBar.visible(false)
    }

    private fun setupGalleryView() {
        val galleryView = findViewById<androidx.recyclerview.widget.RecyclerView>(R.id.gallery_view)
        galleryView.layoutManager = androidx.recyclerview.widget.LinearLayoutManager(this)
        galleryView.adapter = galleryAdapter
    }

}
