package com.ajolla.lactomama.ui.status

import android.app.TimePickerDialog
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityFeedingBinding
import com.ajolla.lactomama.model.StatusDataClass
import com.ajolla.lactomama.viewModel.Viewmodel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class FeedingActivity : AppCompatActivity() {
    private lateinit var binding: ActivityFeedingBinding
    private val calendar = Calendar.getInstance()
    private lateinit var viewmodel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFeedingBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]

        binding.feedingTimeEditeTextView.setOnClickListener {
            showTimePickerDialog(binding.feedingTimeEditeText)
        }

        binding.feedingNote.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {
                // update view
            }

            override fun beforeTextChanged(s: CharSequence, start: Int, count: Int, after: Int) {
                // do nothing
            }

            override fun onTextChanged(s: CharSequence, start: Int, before: Int, count: Int) {
                val deger = binding.amountEditText.text.toString().replace(" ml", "")
                binding.amountEditText.setText("$deger ml")
            }
        })

        binding.nextButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                saveFeedingData()
                println("feeding kaydedildi")
            }
            finish()
        }

        binding.feedingBackButton.setOnClickListener {
            finish()
        }
    }

    private fun showTimePickerDialog(textview: TextView) {
        val timeSetListener = TimePickerDialog.OnTimeSetListener { _, hourOfDay, minute ->
            calendar.set(Calendar.HOUR_OF_DAY, hourOfDay)
            calendar.set(Calendar.MINUTE, minute)
            val time = SimpleDateFormat("HH:mm", Locale.getDefault()).format(calendar.time)
            textview.text = time.toString()
        }

        val hour = calendar.get(Calendar.HOUR_OF_DAY)
        val minute = calendar.get(Calendar.MINUTE)

        TimePickerDialog(this, timeSetListener, hour, minute, true).show()
    }

    private fun saveFeedingData() {
        val feedingTime = binding.feedingTimeEditeText.text.toString()
        val feedingAmount = binding.amountEditText.text.toString()
        val feedingNote = binding.feedingNote.text.toString()

        val date = date()
        val hour = hour()

        val feedingData = StatusDataClass(
            feedingTime = feedingTime,
            feedingAmountMl = feedingAmount,
            feedingNote = feedingNote,
            today = date,
            hour = hour
        )

        viewmodel.insertSleep(feedingData)
    }

    private fun date(): String {
        val dateFormat = SimpleDateFormat("MMMM dd, yyyy (EEEE)", Locale.getDefault())
        val currentDate = Date()
        val today = dateFormat.format(currentDate)
        println(today)
        return today
    }

    private fun hour(): String {
        val dateFormat = SimpleDateFormat("HH:mm")
        val currentDate = Date()
        return dateFormat.format(currentDate)
    }
}