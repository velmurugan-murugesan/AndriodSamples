package com.imageuploader.features.login

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.google.android.material.snackbar.Snackbar
import com.imageuploader.BuildConfig
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.features.dashboard.DashBoardActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.data.repository.UploadRepository
import com.imageuploader.utils.visible
import com.imageuploader.widgets.MyAlertDialog
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : BaseActivity(), LoginView {


    override fun showNoAccess() {
        val myAlertDialog = MyAlertDialog(this@LoginActivity)
        myAlertDialog.setAlertIcon(getDrawable(R.drawable.ic_info_black_24dp))
        myAlertDialog.setAlertDesciption("You do not have permission to access this site or page. Please contact the System Administrator if you wish to have access to this area.")
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

    lateinit var presenter: LoginPresenter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

       /* if (BuildConfig.DEBUG) {
            ed_username.setText("HTCI007")
            ed_password.setText("Htctest@123")
        }*/

        presenter = LoginPresenter(this, UploadRepository())

        button_login.setOnClickListener {
            val username = ed_username.text.toString()
            val password = ed_password.text.toString()

            if (presenter.validateLogin(username, password)) {
                progressBar.visible(true)
                window.setFlags(
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                    WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE
                )
                presenter.doLogin(username, password)
            }
        }
    }

    override fun loginSuccess() {
        progressBar.visible(false)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        AppPreference.saveData(AppConstants.CLIENT_USERNAME, ed_username.text.toString())
        AppPreference.saveData(AppConstants.CLIENT_PASSWORD, ed_password.text.toString())
        launchActivity<DashBoardActivity>()
        finish()
    }



    override fun showMessage(message: String) {
        progressBar.visible(false)
        window.clearFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE)
        Snackbar.make(login_view, message, Snackbar.LENGTH_SHORT).show();
    }

}


