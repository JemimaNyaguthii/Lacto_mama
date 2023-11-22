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
import com.ajolla.lactomama.ui.home.MotherLogin
import com.ajolla.lactomama.viewModel.UserViewModel

class MotherSignUp : AppCompatActivity() {
    lateinit var binding: ActivityMotherSignUpBinding
    val userViewModel: UserViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvNoAccountMother.setOnClickListener {
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnSignUpMother.setOnClickListener {
            clearErrors()
            validateMotherSignUp()
        }
        userViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbregister.visibility = View.GONE
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
            finish()
        })

        userViewModel.successLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show()
            binding.pbregister.visibility = View.GONE
//            intent.putExtra("USER_NAME",username)
            val intent = Intent(this, MotherLogin::class.java)
            startActivity(intent)
            finish()
        })
    }
    fun validateMotherSignUp() {
        val userName = binding.etMotherUsername.text.toString()
        val email = binding.etMotherEmail.text.toString()
        val fullName = binding.etFullNames.text.toString()
        val password = binding.etMotherPassword.text.toString()
        val passwordConfirm = binding.etPasswordConfirmMother.text.toString()
        var error = false


        if (userName.isBlank()) {
            binding.tilMotherUsername.error = "Enter name"
            error = true
        }

        if (email.isBlank() || !email.contains("@")) {
            binding.tilMotherEmail.error = "Invalid Email"
            error = true
        }

        if (fullName.isBlank()) {
            binding.tilMotherFullName.error = "FullName is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilMotherPassword.error = "Password is required"
            error = true
        }
        if (passwordConfirm != password) {
            binding.tilPasswordConfimMother.error = "Confirm your password"
            error = true
        }

        if (!error) {
            val registerRequest = UserRequest(
                userName = userName,
                email = email,
                fullName = fullName,
                password = password
            )
            binding.pbregister.visibility = View.VISIBLE
            userViewModel.registerUser(registerRequest)
        }

    }


    fun clearErrors() {
        binding.tilMotherFullName.error = null
        binding.tilMotherEmail.error = null
        binding.tilMotherUsername.error = null
        binding.tilMotherPassword.error = null
        binding.tilPasswordConfimMother.error = null
        var error = true
    }
}




