package com.imageuploader.features.gallerydetails

import android.content.Intent
import android.graphics.Bitmap
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.target.SimpleTarget
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.createticket.CreateTicketActivity
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.helper.AppConstants
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.activity_gallery_details.*
import okhttp3.Credentials
import android.graphics.drawable.Drawable
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.imageuploader.utils.Utils


class GalleryDetailsActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_gallery_details)

        val heroDetailUrl = intent.getStringExtra(AppConstants.HERO_URL)
        val galleryComments = intent.getStringExtra(AppConstants.GALLERY_COMMENTS)
        val heroDetailImageView = findViewById<ImageView>(R.id.image_hero_details)
        val tv_comments = findViewById<TextView>(com.imageuploader.R.id.tv_comments)
        tv_comments.text = galleryComments

        progressBar.visible(false)

        toolbar.setToolbarClickListener(object : ToolbarClickListener {
            override fun onToolbarClick(view: View) {
                when (view.id) {
                    R.id.img_left_icon -> {
                        onBackPressed()
                        //finish()
                        /*overridePendingTransitionExit()*/
                    }
                }
            }
        })

        if (heroDetailUrl != null) {
            val auth = AppPreference.getStringValue(com.imageuploader.data.appdata.AppConstants.KEY_ACCESS_TOKEN)
            val urlg = GlideUrl(
                heroDetailUrl, LazyHeaders.Builder()
                    .addHeader("Authorization", Credentials.basic(AppPreference.getStringValue(com.imageuploader.data.appdata.AppConstants.CLIENT_USERNAME),AppPreference.getStringValue(
                        com.imageuploader.data.appdata.AppConstants.CLIENT_PASSWORD)))
                    .build()
            )


            Glide.with(this)
                .load(urlg)
                .into(heroDetailImageView)
        }

        button_create_ticket.setOnClickListener {

            val urlg = GlideUrl(
                heroDetailUrl, LazyHeaders.Builder()
                    .addHeader("Authorization", Credentials.basic(AppPreference.getStringValue(com.imageuploader.data.appdata.AppConstants.CLIENT_USERNAME),AppPreference.getStringValue(
                        com.imageuploader.data.appdata.AppConstants.CLIENT_PASSWORD)))
                    .build()
            )

            Glide.with(this)
                .asBitmap()
                .load(urlg)
                .into(object : CustomTarget<Bitmap>(){
                    override fun onResourceReady(resource: Bitmap, transition: Transition<in Bitmap>?) {
                        //imageView.setImageBitmap(resource)


                        val localBitmapUri =
                            Utils.getLocalBitmapUri(resource, this@GalleryDetailsActivity)

                        localBitmapUri?.let {
                            val intent =  Intent(this@GalleryDetailsActivity, CreateTicketActivity::class.java)
                            intent.putStringArrayListExtra("images", arrayListOf(it.toString()))
                            intent.putStringArrayListExtra("urls", arrayListOf(heroDetailUrl))
                            startActivity(intent)
                            finish()
                        }



                    }
                    override fun onLoadCleared(placeholder: Drawable?) {
                        // this is called when imageView is cleared on lifecycle call or for
                        // some other reason.
                        // if you are referencing the bitmap somewhere else too other than this imageView
                        // clear it here as you can no longer have the bitmap
                    }
                })





        }

    }
}
