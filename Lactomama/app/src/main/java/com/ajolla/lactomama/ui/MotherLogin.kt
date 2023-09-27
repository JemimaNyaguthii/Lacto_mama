package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityMotherLoginBinding


class MotherLogin : AppCompatActivity() {
    lateinit var binding: ActivityMotherLoginBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnLogin.setOnClickListener {
            validateLogin()
        }
        binding.tvSignUp.setOnClickListener {
            val intent = Intent(this, MotherSignUp::class.java)
            startActivity(intent)
        }


    }

    override fun onResume() {
        super.onResume()
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
            val intent = Intent(this, MotherProfile::class.java)
            startActivity(intent)
        }
    }

}
