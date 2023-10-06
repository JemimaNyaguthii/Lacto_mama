package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.databinding.ActivityMotherSignUpBinding
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.mother.MotherLogin
import com.ajolla.lactomama.viewModel.UserViewModel

class MotherSignUp : AppCompatActivity() {
    lateinit var binding: ActivityMotherSignUpBinding
    val userViewModel: UserViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.tvNoAccount.setOnClickListener {
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignUp.setOnClickListener {
            clearErrors()
            validateSignUp()
        }
        userViewModel . errorLiveData . observe (this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbregister.visibility = View.GONE
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
            finish()
        })

        userViewModel.successLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
            binding.pbregister.visibility = View.GONE
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
            finish()
        })


     }

       fun validateSignUp() {
           val userName = binding.etFulllNames.text.toString()
        val email = binding.etEmail.text.toString()
        val fullName = binding.etPhoneNumber.text.toString()
        val password = binding.etPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirm.text.toString()
        var error = false

        if (userName.isBlank()) {
            binding.tilFullNames.error = "Enter name"
            error = true
        }
        if (email.isBlank()) {
            binding.tilEmailSignup.error = "Email is required"
            error = true
        }
        if (fullName.isBlank()) {
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
            val registerRequest = UserRequest(
                userName = fullName,
                email = email,
                fullName = userName,
                password = password,
            )
            binding.pbregister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }
    }

    fun clearErrors() {
        binding.tilFullNames.error = null
        binding.tilEmailSignup.error = null
        binding.tilPhoneNumber.error = null
        binding.tilPassword.error = null
        binding.tilPasswordConfim.error = null
        var error = true
    }
}



