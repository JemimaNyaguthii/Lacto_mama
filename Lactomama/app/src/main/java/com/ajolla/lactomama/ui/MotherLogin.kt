package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.databinding.ActivityMotherLoginBinding
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.ui.home.HomePageActivity
import com.ajolla.lactomama.viewModel.LoginViewModel


class MotherLogin : AppCompatActivity() {
    lateinit var binding: ActivityMotherLoginBinding
    val loginViewModel: LoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLogin.setOnClickListener {
            validateLogin()
        }

        loginViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
//            binding.pdLogin.visibility= View.GONE
        })
        loginViewModel.logLiveData.observe(this, Observer { loginResponse ->
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()            // Navigate to the desired activity (e.g., MotherProfile)
            val intent = Intent(this, HomePageActivity::class.java)
            startActivity(intent)
            finish()
        })

        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, MotherSignUp::class.java)
            startActivity(intent)
        }

    }

    fun validateLogin() {
        var email = binding.etEmail.text.toString()
        var password = binding.etLoginPasswordConf.text.toString()
        var error = false
        if (email.isBlank()) {
            binding.tilEmail.error = "Email required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilLoginPasswordConf.error = "Password required"
            error = true
        }
        if (!error) {
            val loginRequest = LoginRequest(

                email = email,
                password = password,
            )
            loginViewModel.loginUser(loginRequest)
        }
    }
}



