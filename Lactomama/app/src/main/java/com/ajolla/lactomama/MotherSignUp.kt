package com.ajolla.lactomama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.ajolla.lactomama.databinding.ActivityMotherSignUpBinding

class MotherSignUp : AppCompatActivity() {
    lateinit var binding: ActivityMotherSignUpBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignUp.setOnClickListener {
            clearErrors()
            validateSignUp()
        }
        binding.tvNoAccount.setOnClickListener {
            val intent = Intent(this,MotherLogin::class.java)
            startActivity(intent)
        }

    }


    fun validateSignUp() {


        val fullName = binding.etFulllNames.text.toString()
        val email = binding.etEmail.text.toString()
        val phoneNumber = binding.etPhoneNumber.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirm.text.toString()

        var error = false

        if (fullName.isBlank()) {
            binding.tilFullNames.error = "Enter name"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmailSignup.error = "Email is required"
            error = true
        }
        if (phoneNumber.isBlank()) {
            binding.tilPhoneNumber.error = "Phone number is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilPassword.error = "Password is required"
            error = true
        }
        if (passwordConfirm != password) {
            binding.tilPasswordConfim.error = "Confirm your password"
            error = true
        }

        if (!error) {
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
            finish()

        }


    }

    fun clearErrors(){
        binding.tilFullNames.error =null
        binding.tilEmailSignup.error = null
        binding.tilPhoneNumber.error = null
        binding.tilPassword.error = null
        binding.tilPasswordConfim.error=null
  }
}
