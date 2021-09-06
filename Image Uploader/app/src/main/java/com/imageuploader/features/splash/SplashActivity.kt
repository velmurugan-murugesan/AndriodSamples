package com.imageuploader.features.splash

import android.os.Bundle
import android.os.Handler
import com.imageuploader.R
import com.imageuploader.base.BaseActivity
import com.imageuploader.features.dashboard.DashBoardActivity
import com.imageuploader.data.appdata.AppConstants
import com.imageuploader.data.appdata.AppPreference
import com.imageuploader.features.login.LoginActivity

class SplashActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.sctivity_splash)
        val runnable = Runnable {

            val user = AppPreference.getStringValue(AppConstants.CLIENT_USERNAME)
           /* AppPreference.saveData(AppConstants.KEY_USERNAME, "HTCI009")
            AppPreference.saveData(AppConstants.KEY_PASSWORD, "outback")*/

            user?.let {
                if(it.length> 1) {
                    launchActivity<DashBoardActivity>()
                    finish()
                } else {
                    launchActivity<LoginActivity>()
                    finish()
                }
            } ?: run {
                launchActivity<LoginActivity>()
                finish()
            }


            /*val loginIntent = Intent(this, LoginActivity::class.java)
            val options = ActivityOptionsCompat.makeSceneTransitionAnimation(this, img_logo, getString(R.string.transition_logo))
            launchActivityWithIntent(loginIntent,options.toBundle()!!)*/

        }

        Handler().postDelayed(runnable, 3000)
    }
}