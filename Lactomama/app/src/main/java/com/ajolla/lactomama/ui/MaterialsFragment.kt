package com.ajolla.lactomama.ui


import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentMaterialsBinding
import com.ajolla.lactomama.model.UploadCoursesRequest
import com.ajolla.lactomama.viewModel.CoursesViewModel
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Locale

class MaterialsFragment : Fragment() {
    lateinit var binding: FragmentMaterialsBinding
    private val startDate = Calendar.getInstance()
    private val endDate = Calendar.getInstance()
    val coursesViewModel: CoursesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentMaterialsBinding.inflate(inflater, container, false)
        val view = binding.root
        val imvStart = view.findViewById<ImageView>(R.id.imvstart)
        val imvEnd = view.findViewById<ImageView>(R.id.imvend)
        imvStart.setOnClickListener {
            showDatePickerDialog(binding.etstartdate)
        }
        imvEnd.setOnClickListener {
            showDatePickerDialog(binding.etend)
        }
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val uploadButton = view.findViewById<Button>(R.id.btnupload)
        uploadButton.setOnClickListener {
            if (isValidInput()) {
                uploadCourse()
            } else {
                Toast.makeText(requireContext(), "Failed to fill in the form", Toast.LENGTH_SHORT).show()
            }
        }
    }

    private fun uploadCourse() {
        val etTitle = binding.ettitle.text.toString()
        val etDescription = binding.etdes.text.toString()
        val etStartDate = binding.etstartdate.text.toString()
        val etEndDate = binding.etend.text.toString()
        val etPrice = binding.etprice.text.toString()

        val coursesRequest = UploadCoursesRequest(etTitle, etDescription, etStartDate, etEndDate, etPrice)

        lifecycleScope.launch{
            try {
                val isSuccess = coursesViewModel.uploadCourse(coursesRequest)
                if (isSuccess) {
                    Toast.makeText(requireContext(), "Uploaded Successfully", Toast.LENGTH_SHORT).show()
                    val intent = Intent(requireContext(), Success::class.java)
                    startActivity(intent)

                } else {
                    Toast.makeText(requireContext(), "Upload failed", Toast.LENGTH_SHORT).show()
                }
            } catch (e: Exception) {
                Log.e("MaterialsFragment", "Error uploading course", e)
                Toast.makeText(requireContext(), "An error occurred during upload", Toast.LENGTH_SHORT).show()
            }
        }
    }

    fun showDatePickerDialog(editText: EditText) {
        val calendar = if (editText.id == R.id.etstartdate) startDate else endDate
        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
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

    private fun isValidInput(): Boolean {
        val etTitle = binding.ettitle
        val etDescription = binding.etdes
        val etStartDate = binding.etstartdate
        val etEndDate = binding.etend
        val etPrice = binding.etprice
        var isValid = true

        if (TextUtils.isEmpty(etTitle.text.toString())) {
            etTitle.error = "Title is required"
            isValid = false
        } else {
            etTitle.error = null
        }
        if (TextUtils.isEmpty(etDescription.text.toString())) {
            etDescription.error = "Description is required"
            isValid = false
        } else {
            etDescription.error = null
        }
        if (TextUtils.isEmpty(etStartDate.text.toString())) {
            etStartDate.error = "Start date is required"
            isValid = false
        } else {
            etStartDate.error = null
        }
        if (TextUtils.isEmpty(etEndDate.text.toString())) {
            etEndDate.error = "End date is required"
            isValid = false
        } else {
            etEndDate.error = null
        }
        if (TextUtils.isEmpty(etPrice.text.toString())) {
            etPrice.error = "Price is required"
            isValid = false
        } else {
            etPrice.error = null
        }
        return isValid
    }
}

