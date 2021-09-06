package com.imageuploader.features.galleryview

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView

import com.bumptech.glide.Glide
import com.imageuploader.R
import com.imageuploader.interfaces.ClickListener
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.load.model.GlideUrl
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.activity_image_upload.view.*
import kotlinx.android.synthetic.main.hero_item.view.*
import okhttp3.Credentials


class GalleryImageAdapter(private val mContext: Context) :
    androidx.recyclerview.widget.RecyclerView.Adapter<GalleryImageAdapter.MyViewHolder>() {
    // private var mHeroes: List<RequestModel.Gallery>? = null
    var mHeroes = mutableListOf<String>()
    private var selectedPhotos = mutableListOf<String>()

    private var isLocal = false
    private var isHideIcon = false
    private var clickListener: ClickListener? = null

    var selectedImages = mutableListOf<String>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, i: Int): MyViewHolder {
        val v = LayoutInflater.from(viewGroup.context).inflate(R.layout.hero_item, viewGroup, false)
        return MyViewHolder(v)
    }

    override fun onBindViewHolder(myViewHolder: MyViewHolder, i: Int) {

        val hero = mHeroes!![i]



        if (!isLocal) {
            val url = "${AppConstants.BASE_IMAGE_URL}/$hero"
            Log.d("url = ", url)

            if (selectedImages.contains(url) && !isHideIcon) {
                myViewHolder.selectedImage.visible(true)
            } else {
                myViewHolder.selectedImage.visible(false)
            }


            val auth = AppPreference.getStringValue(AppConstants.KEY_ACCESS_TOKEN)
            val urlg = GlideUrl(
                url, LazyHeaders.Builder()
                    .addHeader(
                        "Authorization",
                        Credentials.basic(
                            AppPreference.getStringValue(AppConstants.CLIENT_USERNAME),
                            AppPreference.getStringValue(AppConstants.CLIENT_PASSWORD)
                        )
                    )
                    .build()
            )
            Glide.with(mContext).load(urlg)
                .centerCrop()
                .placeholder(R.drawable.place_holder)
                .into(myViewHolder.heroImage)
        } else {

            if (selectedImages.contains(hero) && !isHideIcon) {
                myViewHolder.selectedImage.visible(true)
            } else {
                myViewHolder.selectedImage.visible(false)
            }

            Glide.with(mContext)
                .load(hero).centerCrop()
                .into(myViewHolder.heroImage);
        }

    }

    override fun getItemCount(): Int {
        return mHeroes!!.size
    }

    /*fun setHeros(heroes: List<RequestModel.Gallery>) {
        this.mHeroes = heroes
        notifyDataSetChanged()
    }
*/

    fun addSelectedPhotos(heroes: MutableList<String>, isHideIcon: Boolean) {
        this.selectedPhotos = heroes
        this.isHideIcon = isHideIcon
    }


    fun setHeros(heroes: MutableList<String>, isLocal: Boolean) {
        this.mHeroes.clear()
        this.mHeroes.addAll(heroes)
        this.mHeroes.addAll(selectedPhotos)
        this.isLocal = isLocal
        notifyDataSetChanged()
    }

    fun setOnclickListener(clickListener: ClickListener) {
        this.clickListener = clickListener
    }


    fun setSelectedItem(map: MutableMap<String, Set<String>>) {
        selectedImages.clear()
        map.forEach { s, list ->
            list.forEach {
                selectedImages.add(it)
            }
        }
        notifyDataSetChanged()
    }

    inner class MyViewHolder internal constructor(itemView: View) :
        androidx.recyclerview.widget.RecyclerView.ViewHolder(itemView) {
        val heroImage: ImageView = itemView.findViewById(R.id.hero_image)
        val selectedImage = itemView.img_selected

        init {
            heroImage.setOnClickListener { v ->
                mHeroes?.get(adapterPosition)?.let {
                    clickListener?.onClickItem(
                        v, adapterPosition, "${AppConstants.BASE_IMAGE_URL}/$it"
                    )
                }
            }
        }
    }
}
