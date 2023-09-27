package com.ajolla.lactomama.ui


import android.app.DatePickerDialog
import android.content.Intent
import android.icu.util.Calendar
import android.os.Bundle
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityUploadMaterialsBinding
import java.text.SimpleDateFormat
import java.util.Locale



class MaterialsFragment : Fragment() {
    lateinit var binding: ActivityUploadMaterialsBinding
    private val Startdate = Calendar.getInstance()
    private val enddate = Calendar.getInstance()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_materials, container, false)

        val imvStart = view.findViewById<ImageView>(R.id.imvstart)
        val imvEnd = view.findViewById<ImageView>(R.id.imvend)

        imvStart.setOnClickListener {
            showDatePickerDialog(view.findViewById(R.id.etstartdate))
        }

        imvEnd.setOnClickListener {
            showDatePickerDialog(view.findViewById(R.id.etend))
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val etTitle = view.findViewById<EditText>(R.id.ettitle)
        val etDescription = view.findViewById<EditText>(R.id.etdes)
        val etStartDate = view.findViewById<EditText>(R.id.etstartdate)
        val etEndDate = view.findViewById<EditText>(R.id.etend)
        val etPrice = view.findViewById<EditText>(R.id.etprice)

        val uploadButton = view.findViewById<Button>(R.id.btnupload)
        uploadButton.setOnClickListener {
            if (isValidInput(etTitle, etDescription, etStartDate, etEndDate, etPrice)) {

                navigateToSuccess()

            } else {
                Toast.makeText(requireContext(), "Failed fill in the form", Toast.LENGTH_SHORT).show()

            }
        }
    }

    private fun navigateToSuccess() {
        val intent = Intent(activity, Success::class.java)
        startActivity(intent)
        Toast.makeText(requireContext(), " Successful", Toast.LENGTH_SHORT).show()
    }


    fun showDatePickerDialog(editText: EditText) {
        val calendar = if (editText.id == R.id.etstartdate) Startdate else enddate

        val datePickerDialog = DatePickerDialog(
            requireContext(),
            DatePickerDialog.OnDateSetListener { _, year, monthOfYear, dayOfMonth ->
                calendar.set(year, monthOfYear, dayOfMonth)
                val formattedDate =
                    SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(calendar.time)
                editText.setText(formattedDate)
            },
            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DAY_OF_MONTH)
        )
        datePickerDialog.show()
    }

    private fun isValidInput(
        etTitle: EditText,
        etDescription: EditText,
        etStartDate: EditText,
        etEndDate: EditText,
        etPrice: EditText
    ): Boolean {
        val title = etTitle.text.toString()
        val description = etDescription.text.toString()
        val startDate = etStartDate.text.toString()
        val endDate = etEndDate.text.toString()
        val price = etPrice.text.toString()

        var isValid = true

        if (TextUtils.isEmpty(title)) {
            etTitle.error = "Title is required"
            isValid = false
        } else {
            etTitle.error = null
        }

        if (TextUtils.isEmpty(description)) {
            etDescription.error = "Description is required"
            isValid = false
        } else {
            etDescription.error = null
        }

        if (TextUtils.isEmpty(startDate)) {
            etStartDate.error = "Start date is required"
            isValid = false
        } else {
            etStartDate.error = null
        }

        if (TextUtils.isEmpty(endDate)) {
            etEndDate.error = "End date is required"
            isValid = false
        } else {
            etEndDate.error = null
        }

        if (TextUtils.isEmpty(price)) {
            etPrice.error = "Price is required"
            isValid = false
        } else {
            etPrice.error = null
        }



        return isValid
    }
}




