package com.ajolla.lactomama.ui

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
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


//    fun isLogged(context: Context):Boolean{
//        val  sharedPreferences:SharedPreferences=
//            context.getSharedPreferences("auth_prefs",Context.MODE_PRIVATE)
//        return sharedPreferences.getBoolean("is_logged_in",false)
//    }

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
        val firstName = binding.etFirstName.text.toString()
        val secondName = binding.etSecondName.text.toString()
        val email = binding.etEmailLactationist.text.toString()
        val bio = binding.etBio.text.toString()
        val password = binding.etLactationistLoginPassword.text.toString()
        val passwordConfirm = binding.etLactationistLoginConfirm.text.toString()
        var error = false

        if (firstName.isBlank()) {
            binding.tilFirstName.error = "Enter First name"
            error = true
        }

        if (secondName.isBlank()) {
            binding.tilSecondName.error = "Enter Second name"
            error = true
        }

        if (email.isBlank() || !isValidEmail(email)) { // Check for a valid email
            binding.tilEmailLactationist.error = "Enter a valid email"
            error = true
        }

        if (bio.isBlank()) {
            binding.tilBio.error = "Bio is required"
            error = true
        }

        if (password.isBlank()) {
            binding.tilLactationistLoginPassword.error = "Password is required"
            error = true
        }

        if (passwordConfirm.isBlank()) {
            binding.tilLactationistLoginConfirm.error = "Confirm your password"
            error = true
        }

        if (password != passwordConfirm) {
            binding.tilLactationistLoginPassword.error = "Passwords do not match"
            binding.tilLactationistLoginConfirm.error = "Passwords do not match"
            error = true
        }

        if (!error) {
            val lactationistRequest = LactationistRequest(
                FirstName = firstName,
                SecondName = secondName,
                email = email,
                bio = bio,
                password = password
            )
            binding.pbregister.visibility = View.VISIBLE
            lactationistViewModel.registerLactationist(lactationistRequest)
        }
    }

    private fun isValidEmail(email: String): Boolean {
        return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
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
