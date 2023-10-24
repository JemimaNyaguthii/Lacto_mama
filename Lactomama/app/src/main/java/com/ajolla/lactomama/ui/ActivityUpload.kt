package com.ajolla.lactomama.ui

import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityUploadBinding
import com.ajolla.lactomama.model.CredentialRequest
import com.ajolla.lactomama.viewModel.CredentialViewModel

import java.text.SimpleDateFormat
import java.util.Locale

class ActivityUpload : AppCompatActivity() {
    private lateinit var binding: ActivityUploadBinding
    val credentialViewModel:CredentialViewModel by viewModels()
    private var selectedFileUri: Uri? = null
    private var fileUploaded = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUploadBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnVerify.setOnClickListener {

            clearErrors()
            validateLogin()
        }
        binding.ivUpload.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "*/*"
            startActivityForResult(intent, 1)
        }
        credentialViewModel . errorLiveData . observe (this, Observer { err ->
            Toast.makeText(this, err, Toast.LENGTH_LONG).show()
            binding.pbRegister.visibility = View.GONE
            val intent = Intent(this,  ActivityUpload::class.java)
            startActivity(intent)
            finish()
        })

        credentialViewModel.credLiveData.observe(this, Observer{ credResponse ->
            Toast.makeText(this, "Signed in successfully", Toast.LENGTH_SHORT).show()
            binding.pbRegister.visibility = View.GONE
            val intent = Intent(this, SuceessScreen::class.java)
            startActivity(intent)
            finish()
        })


    }

    @Deprecated("Deprecated in Kotlin")
    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == RESULT_OK) {
            selectedFileUri = data?.data
            Toast.makeText(this, "File uploaded successfully", Toast.LENGTH_SHORT).show()
            fileUploaded = true
        }

    }


    override fun onResume() {
        super.onResume()

    }
    fun validateLogin() {
        var lnumber = binding.etLicenceNumber.text.toString()
        var issuedBy = binding.etIssuedBy.text.toString()
        var dateIssued = binding.etDateIssued.text.toString()
        var status=binding.etstatus.text.toString()
        var selectedFileUri = selectedFileUri.toString()

        var error = false
        if (lnumber.isBlank()) {
            binding.tilLnumber.error = "Email required"
            error = true
        }
        if (issuedBy.isBlank()) {
            binding.tilIssued.error = "Password required"
            error = true
        }

        if (dateIssued.isBlank()) {
            binding.tilDateIssued.error = "Password required"
            error = true
        }
        if (status.isBlank()) {
            binding.tilDescription.error = " lisense status required"
            error = true
        }
        if (selectedFileUri == null) {
            // Show an error message for missing file
            Toast.makeText(this, "Please upload a license file", Toast.LENGTH_SHORT).show()
            error = true
        }


        if (!error) {
            val credentialRequest = CredentialRequest(
                lnumber =lnumber,
                issuedBy =issuedBy,
                dateIssued = dateIssued,
                licenseFile = selectedFileUri?.toString() ?: "pdf"
            )
            binding.pbRegister.visibility = View.VISIBLE
            credentialViewModel.credentialUser(credentialRequest)
        }
    }
    private val dateIssuedCalendar = Calendar.getInstance()
    private val expiryCalendar = Calendar.getInstance()

    fun showDatePickerDialog(view: View) {
        val editText = view as EditText
        val calendar = if (editText.id == R.id.etDateIssued) dateIssuedCalendar else expiryCalendar

        val datePickerDialog = DatePickerDialog(
            this,
            { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val formattedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
                editText.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }


    fun clearErrors(){
        binding.tilLnumber.error =null
        binding.tilIssued.error = null
        binding.tilDateIssued.error = null


    }
}











