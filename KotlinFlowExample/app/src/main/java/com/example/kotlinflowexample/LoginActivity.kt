package com.example.kotlinflowexample

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.addTextChangedListener
import androidx.core.widget.doOnTextChanged
import androidx.databinding.DataBindingUtil
import com.example.kotlinflowexample.databinding.ActivityLoginBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    val loginViewModel: LoginViewModel by viewModels()
    
    lateinit var loginBinding: ActivityLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        loginBinding = DataBindingUtil.setContentView(this,R.layout.activity_login)
        loginBinding.editTextUsername.addTextChangedListener {
            loginViewModel.setUsername(it.toString())
        }

        loginBinding.editTextPassword.addTextChangedListener {
            loginViewModel.setPassword(it.toString())
        }

        loginBinding.editTextEmail.addTextChangedListener {
            loginViewModel.setEmail(it.toString())
        }

        CoroutineScope(Dispatchers.IO).launch {
            loginViewModel.isLoginValid.collect {
                withContext(Dispatchers.Main) {
                    Toast.makeText(this@LoginActivity, "Login $it", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }



}