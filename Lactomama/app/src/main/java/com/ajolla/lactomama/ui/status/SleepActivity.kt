package com.ajolla.lactomama.ui.status

import android.app.TimePickerDialog
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivitySleepBinding
import com.ajolla.lactomama.model.StatusDataClass
import com.ajolla.lactomama.viewModel.Viewmodel
import java.text.SimpleDateFormat
import java.util.*

class SleepActivity : AppCompatActivity() {
    private lateinit var binding: ActivitySleepBinding
    private val calendar = Calendar.getInstance()
    private lateinit var viewmodel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySleepBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewmodel = ViewModelProvider(this)[Viewmodel::class.java]

        binding.fellSleepView.setOnClickListener {
            showTimePickerDialog(binding.fellSleepTextview)
        }

        binding.wakeupView.setOnClickListener {
            showTimePickerDialog(binding.wakeupTextview)
        }

        binding.nextButton.setOnClickListener {
            println("sleep saved")
            saveSleepData()
            finish()
        }

        binding.sleepBackButton.setOnClickListener {
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
    private fun saveSleepData() {
        val fellSleep = binding.fellSleepTextview.text.toString()
        val  wakeUpSleepTime= binding.wakeupTextview.text.toString()
        val sleepNote = binding.sleepNote.text.toString()

        val diaper = StatusDataClass(
            feelSleep = fellSleep,
            wakeupSleep = wakeUpSleepTime,
            sleepNote = sleepNote,
            today = date(),
            hour = hour()
        )

        viewmodel.insertSleep(diaper)
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