package com.imageuploader.features.dashboard

import android.os.Bundle
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.style.ForegroundColorSpan
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AlertDialog
import com.google.android.material.snackbar.Snackbar
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.createticket.CreateTicketActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.features.galleryview.GalleryActivity
import com.imageuploader.features.imageupload.ImageUploadActivity
import com.imageuploader.interfaces.ClickListener
import com.imageuploader.interfaces.ToolbarClickListener
import com.imageuploader.features.login.LoginActivity
import com.imageuploader.features.viewticket.ViewTicketActivity
import com.imageuploader.utils.visible
import kotlinx.android.synthetic.main.activity_dashboard.*
import kotlinx.android.synthetic.main.activity_dashboard.progressBar
import android.widget.Toast
import com.imageuploader.widgets.MyAlertDialog




class DashBoardActivity : BaseActivity(), DashboardView {

    override fun authIssue() {
        launchActivity<LoginActivity>()
        finish()
    }

    override fun showProgressbar() {
        progressBar.visibility = View.VISIBLE
    }

    override fun hideProgressbar() {
        progressBar.visibility = View.GONE
    }

    override fun updateTicketCount(ticketCount: Int) {
        adapter.showTicketCount(ticketCount)
        hideProgressbar()
    }


    lateinit var adapter: DashbaordAdapter
    lateinit var presenter: DashboardPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_dashboard)

        presenter = DashboardPresenter(this)

        toolbar.setToolbarClickListener(object : ToolbarClickListener {
            override fun onToolbarClick(view: View) {
                when (view.id) {

                    R.id.img_right_icon -> {

                    }

                    R.id.img_left_icon-> {
                        val title = "Are you sure want to logout ?"
                        val myAlertDialog = MyAlertDialog(this@DashBoardActivity)
                        myAlertDialog.setAlertIcon(getDrawable(R.drawable.ic_info_black_24dp)!!)
                        myAlertDialog.setAlertDesciption(title)

                        myAlertDialog.setOnActionListener(object :
                            MyAlertDialog.DialogActionListener {
                            override fun onAction(view: View) {

                                when (view.id) {
                                    R.id.btn_left -> {
                                        myAlertDialog.dismiss()
                                    }
                                    R.id.btn_right -> {
                                        myAlertDialog.dismiss()
                                        AppPreference.saveData(AppConstants.CLIENT_USERNAME, "")
                                        AppPreference.saveData(AppConstants.CLIENT_PASSWORD, "")
                                        launchActivity<LoginActivity>()
                                        finish()
                                    }
                                }

                            }
                        })
                        myAlertDialog.show()




                        /*val alertDialog = AlertDialog.Builder(this@DashBoardActivity)
                            .setPositiveButton("Cancel") { dialog, which ->
                                dialog?.dismiss()
                            }
                            .setNegativeButton("Yes"
                            ) { dialog, which ->
                                dialog?.dismiss()
                                AppPreference.saveData(AppConstants.CLIENT_USERNAME, "")
                                AppPreference.saveData(AppConstants.CLIENT_PASSWORD, "")
                                launchActivity<LoginActivity>()
                                finish()
                                 }


                        // Initialize a new foreground color span instance
                        val foregroundColorSpan = ForegroundColorSpan(getColor(R.color.grey_dark));

                        // Initialize a new spannable string builder instance
                        val ssBuilder = SpannableStringBuilder(title);

                        // Apply the text color span
                        ssBuilder.setSpan(
                            foregroundColorSpan,
                            0,
                            title.length,
                            Spanned.SPAN_EXCLUSIVE_EXCLUSIVE
                        );
                        alertDialog.setTitle(ssBuilder)
                        alertDialog.show()*/
                    }
                }
            }
        })


        adapter = DashbaordAdapter()
        recyclerview_menu.adapter = adapter
        text_profile_name.text = AppPreference.getStringValue(AppConstants.CLIENT_USERNAME)
        adapter.addItemClickListener(object : ClickListener{
            override fun onClickItem(view: View, id: Int, data: String) {
                when(id) {

                    0 -> {
                        val canCreateTicket =
                            AppPreference.getBooleanValue(AppConstants.KEY_CREATE_TICKET)
                        if(canCreateTicket){
                            launchActivity<CreateTicketActivity>()
                        } else {
                            val myAlertDialog = MyAlertDialog(this@DashBoardActivity)
                            myAlertDialog.setAlertIcon(getDrawable(R.drawable.ic_info_black_24dp)!!)
                            myAlertDialog.setAlertDesciption("Please contact your section administrator. Permissions need to be completed.")
                            myAlertDialog.setMiddleButtonText("Ok")
                            myAlertDialog.setOnActionListener(object :
                                MyAlertDialog.DialogActionListener {
                                override fun onAction(view: View) {

                                    when (view.id) {
                                        R.id.btn_middle -> {
                                            myAlertDialog.dismiss()
                                        }
                                    }

                                }
                            })
                            myAlertDialog.show()
                        }
                    }

                    1 -> {
                        launchActivity<ViewTicketActivity>()
                    }

                    2 -> {
                        launchActivity<ImageUploadActivity>()
                    }

                    3 -> {
                        launchActivity<GalleryActivity>()
                    }
                }
            }
        })


    }
    fun showMessage(message: String) {
        Snackbar.make(recyclerview_menu, message, Snackbar.LENGTH_SHORT).show();
    }

    override fun onResume() {
        super.onResume()
        presenter.fetchTicketCount()
    }

}