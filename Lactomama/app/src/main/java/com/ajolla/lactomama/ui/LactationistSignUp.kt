package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityLactationistSignUpBinding
import com.ajolla.lactomama.databinding.ActivityMotherSignUpBinding
import com.ajolla.lactomama.model.LactationistRequest
import com.ajolla.lactomama.model.UserRequest
import com.ajolla.lactomama.ui.home.MotherLogin
import com.ajolla.lactomama.viewModel.ArticlesViewModel
import com.ajolla.lactomama.viewModel.LactationistViewModel
import com.ajolla.lactomama.viewModel.UserViewModel

class LactationistSignUp : AppCompatActivity() {

    lateinit var binding: ActivityLactationistSignUpBinding
    val lactationistViewModel: LactationistViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLactationistSignUpBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.tvLactaitonistLogin.setOnClickListener {
          val intent = Intent(this, LactationistLogin::class.java)
           startActivity(intent)
        }
    }

    override fun onResume() {
        super.onResume()
        binding.btnLactationistsSignUp.setOnClickListener {
            clearErrors()
            validateSignUp()
        }
        lactationistViewModel.errorLiveData.observe(this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbregister.visibility = View.GONE
            val intent = Intent(this, LactationistHome::class.java)
            startActivity(intent)
            finish()
        })

        lactationistViewModel.successLiveData.observe(this, Observer { regResponse ->
            Toast.makeText(this, "Sign in success", Toast.LENGTH_SHORT).show()
            binding.pbregister.visibility = View.GONE
            val intent = Intent(this, ActivityUpload::class.java)
            startActivity(intent)
            finish()
        })
    }


    fun validateSignUp() {
        val FirstName = binding.etFirstName.text.toString()
        val SecondName = binding.etSecondName.text.toString()
        val Email = binding.etEmailLactationist.text.toString()
        val Bio = binding.etBio.text.toString()
        val password = binding.etLactationistLoginPassword.text.toString()
        val passwordConfirm = binding.etLactationistLoginConfirm.text.toString()
        var error = false

        if (FirstName.isBlank()) {
            binding.tilFirstName.error = "Enter First name"
            error = true
        }

        if (SecondName.isBlank() ) {
            binding.tilSecondName.error = "Enter Second name"
            error = true
        }

        if (Email.isBlank()) {
            binding.tilFirstName.error = "Email is required"
            error = true
        }
        if (Bio.isBlank()) {
            binding.tilBio.error = "Bio is required"
            error = true
        }
        if (password.isBlank()) {
            binding.tilLactationistLoginPassword.error = "password is required"
            error = true
//            tilLactationistLoginPassword
        }
        if (passwordConfirm.isBlank()) {
            binding.tilLactationistLoginConfirm.error = "Confrim your password"
            error = true
//            tilLactationistLoginConfirm
        }

        if (!error) {
            val lactationistRequest = LactationistRequest(
                FirstName = FirstName,
                SecondName = SecondName,
                email = Email,
                bio = Bio,
                password = password,
            )
            binding.pbregister.visibility = View.VISIBLE
            lactationistViewModel.registerLactationist(lactationistRequest)
        }

    }


    fun clearErrors() {
        binding.tilFirstName.error = null
        binding.tilSecondName.error = null
        binding.tilEmailLactationist.error = null
        binding.tilBio.error = null
        binding.tilLactationistLoginPassword.error = null
        binding.tilLactationistLoginConfirm.error = null
        var error = true
    }
}

