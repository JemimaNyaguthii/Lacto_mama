package com.ajolla.lactomama.lactationist

import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.DatePicker
import android.widget.EditText
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.lifecycleScope
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityLactationistDetailsBinding
import com.ajolla.lactomama.ui.bookings.BookingsFragment
import com.ajolla.lactomama.ui.bookings.BookingsRequest
import com.ajolla.lactomama.viewModel.BookingsViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class LactationistDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityLactationistDetailsBinding
    val dateIssuedCalendar = Calendar.getInstance()
    val expiryCalendar = Calendar.getInstance()
    val bookingsViewModel: BookingsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityLactationistDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.ivArrowBack.setOnClickListener {
            finish()
        }

        binding.btnBookingAppointment.setOnClickListener {
            val bookingRequest = prepareBookingRequest()
            lifecycleScope.launch(Dispatchers.IO) {
                bookingsViewModel.bookingLactationist(bookingRequest, bookingRequest.lactationist.toInt())
            }
        }

        val firstName = intent.getStringExtra("FIRST_NAME")
        val description = intent.getStringExtra("BIO")
        populateLactationistDetails(firstName, description)
    }

    private fun prepareBookingRequest(): BookingsRequest {
        val selectedDate = findViewById<EditText>(R.id.etDate).text.toString()
        val need = findViewById<EditText>(R.id.etNeed).text.toString()
        val motherName=intent.getStringExtra("FIRST_NAME") ?: ""
        val lactationistId = findViewById<EditText>(R.id.etLactationist).text.toString().toInt()

        return BookingsRequest(
            availableSlot = selectedDate,
            need = need,
            lactationist = lactationistId,
            firstName =motherName
        )
    }

    override fun onResume() {
        super.onResume()
        bookingsViewModel.bookingLiveData.observe(this, Observer { response ->
            Toast.makeText(
                baseContext, "The Booking was success", Toast.LENGTH_LONG
            ).show()
        })
        bookingsViewModel.errorLiveData.observe(this, Observer { str ->
            Toast.makeText(baseContext, str, Toast.LENGTH_LONG).show()
        })
    }

    private fun populateLactationistDetails(firstName: String?, description: String?) {
        binding.tvLactationistName.text = firstName.toString()
        binding.tvDescription.text = description.toString()
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

    fun showBookingSuccessToast() {
        val editTextDate = findViewById<EditText>(R.id.etDate)
        val selectedDateText = editTextDate.text.toString()

        if (isValidDate(selectedDateText)) {
            val selectedDate =
                SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).parse(selectedDateText)
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
}



//this is my code fotr the lactationist details activity.The ivArrowBack does not work and also the populate lactationistdetails function the one that has alsso the intents does not display the details of the lactationist you have selected until  you press the btnBookingAppointment button and this is after you the booking is a success.also when you press the btnBookingAppointment before you input the details,it is taking you to another page and it is not supposed to work like that would you please help me with that.