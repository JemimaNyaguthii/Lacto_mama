package com.ajolla.lactomama.ui

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.Contants
import com.ajolla.lactomama.databinding.ActivityLactationistLoginBinding
import com.ajolla.lactomama.model.LactationistLoginRequest
import com.ajolla.lactomama.model.LoginRequest
import com.ajolla.lactomama.model.LoginResponse
import com.ajolla.lactomama.viewModel.LactationistLoginViewModel
import com.ajolla.lactomama.viewModel.LactationistViewModel
import com.ajolla.lactomama.viewModel.LoginViewModel

class LactationistLogin : AppCompatActivity() {
 lateinit var binding: ActivityLactationistLoginBinding
    val lactationistLoginViewModel: LactationistLoginViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLactationistLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnLoginLact.setOnClickListener {
            validateLogin()
        }
     lactationistLoginViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbLogin.visibility= View.GONE
        })

        lactationistLoginViewModel.lactLogLiveData.observe(this, Observer { logResponse ->
            Toast.makeText(this, "Login successfully", Toast.LENGTH_SHORT).show()
            binding.pbLogin.visibility = View.GONE
            val intent = Intent(this, ActivityUpload::class.java)
            startActivity(intent)
            finish()
        })
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, LactationistSignUp::class.java)
            startActivity(intent)
        }

    }

    fun validateLogin() {
        var email = binding.etEmailLactationistLogin.text.toString()
        var password = binding.etEmailPasswordLogin.text.toString()
        var error = false
        if (email.isBlank()) {
            binding.tilEmailLoginLactationist.error = "Email required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilEmailPasswordLogin.error = "Password required"
            error = true
        }

        if(!error){
            val lactationistLoginRequest = LactationistLoginRequest(
                email = email,
                password = password,
                success = true

            )
            binding.pbLogin.visibility = View.VISIBLE
            lactationistLoginViewModel.loginLactationist(lactationistLoginRequest)
        }

    }
//    fun persistLogin(loginResponse: LoginResponse){
//        val sharedPrefs=getSharedPreferences(Contants.PREFS,Context.MODE_PRIVATE)
//        val editor= sharedPrefs.edit()
//        editor.putString(Contants.USER_ID,loginResponse.uesr_id)
//        editor.putString(Contants.ACCESS_TOKEN,)

}

