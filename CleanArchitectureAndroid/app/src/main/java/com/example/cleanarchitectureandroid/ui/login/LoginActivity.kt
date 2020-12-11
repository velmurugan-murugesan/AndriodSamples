package com.example.cleanarchitectureandroid.ui.login

import android.content.Intent
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import com.example.cleanarchitectureandroid.R
import com.example.cleanarchitectureandroid.databinding.ActivityLoginBinding
import com.example.cleanarchitectureandroid.ui.home.HomeActivity
import dagger.android.support.DaggerAppCompatActivity
import javax.inject.Inject

class LoginActivity : DaggerAppCompatActivity() {

    lateinit var loginBinding: ActivityLoginBinding

    @Inject
    lateinit var viewModelFactory: ViewModelProvider.Factory

    lateinit var loginViewModel: LoginViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this, R.layout.activity_login)
        loginBinding.lifecycleOwner = this
        loginViewModel = ViewModelProviders.of(this,viewModelFactory).get(LoginViewModel::class.java)

        loginBinding.loginViewModel = loginViewModel

        observeChanges()
    }

    private fun observeChanges() {
        loginViewModel.loginSuccess.observe(this, {
            it?.let {
                startActivity(Intent(this@LoginActivity, HomeActivity::class.java))
                finish()
            }
        })
    }
}