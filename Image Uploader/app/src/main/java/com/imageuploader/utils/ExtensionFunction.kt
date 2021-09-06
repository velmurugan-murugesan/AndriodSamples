package com.imageuploader.utils

import android.content.Context
import android.content.res.ColorStateList
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorRes
import androidx.annotation.StringRes
import androidx.core.view.ViewCompat
import com.imageuploader.base.MyApp

fun View.visible(status: Boolean) = if (status) this.visibility = View.VISIBLE else this.visibility = View.GONE

fun TextView.setTextViewColor(@ColorRes color: Int) = this.setTextColor(MyApp.instance.getColor(color))

fun ImageView.setBackgroundTintColor(@ColorRes color: Int) =
    ViewCompat.setBackgroundTintList(this, ColorStateList.valueOf(MyApp.instance.getColor(color)))



fun Any.getString(context: Context, @StringRes id:Int) : String {
    return context.getString(id)
}


/*fun Fragment.replaceFragment(fragment: Fragment, tag: String, isAddToBackStack: Boolean) {
    val fragmentTransaction = this.fragmentManager?.beginTransaction()
        ?.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_left, R.anim.slide_in_right, R.anim.slide_out_right)
        ?.replace(R.id.frameLayout, fragment, tag);

    if (isAddToBackStack) {
        fragmentTransaction?.addToBackStack(null)
    }
    fragmentTransaction?.commit()

}*/
