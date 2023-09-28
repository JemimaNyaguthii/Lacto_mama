package com.ajolla.lactomama.ui

import android.app.DatePickerDialog
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.FrameLayout
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityLactationistDetailsBinding
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class LactationistDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLactationistDetailsBinding
    val dateIssuedCalendar = Calendar.getInstance()
    val expiryCalendar = Calendar.getInstance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLactationistDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnBookingAppointment.setOnClickListener {
            showBookingSuccessToast()
        }
    }


    fun showDatePickerDialog(view: View) {
        val editText = view as EditText
        val calendar =
            if (editText.id == R.id.etDate) dateIssuedCalendar else expiryCalendar

        val datePickerDialog = DatePickerDialog(
            this,
            { _: DatePicker, year: Int, monthOfYear: Int, dayOfMonth: Int ->
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
    fun showBookingSuccessToast() {
        val editTextDate = findViewById<EditText>(R.id.etDate)
        val selectedDateText = editTextDate.text.toString()

        if (isValidDate(selectedDateText)) {
            val selectedDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(selectedDateText)
            if (isAllowedAppointmentDay(selectedDate)) {
                val inflater = layoutInflater
                val layout = inflater.inflate(R.layout.toastlayout, findViewById(R.id.toast_layout))
                val toast = Toast(applicationContext)
                toast.duration = Toast.LENGTH_LONG
                toast.view = layout
                toast.show()
            } else {
                Toast.makeText(
                    this,
                    "Appointments are only allowed on Mondays and Wednesdays.",
                    Toast.LENGTH_LONG
                ).show()
            }
        } else {
            Toast.makeText(
                this,
                "Please select a valid date before booking.",
                Toast.LENGTH_LONG
            ).show()
        }
    }

    fun isValidDate(dateText: String): Boolean {
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            dateFormat.isLenient = false
            dateFormat.parse(dateText)
            return true
        } catch (e: Exception) {
            return false
        }
    }

    fun isAllowedAppointmentDay(selectedDate: Date): Boolean {
        val calendar = Calendar.getInstance()
        calendar.time = selectedDate
        val dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK)
        return dayOfWeek == Calendar.MONDAY || dayOfWeek == Calendar.WEDNESDAY
    }



}



