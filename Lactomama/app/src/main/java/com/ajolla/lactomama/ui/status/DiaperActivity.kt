package com.ajolla.lactomama.ui.status

import android.app.TimePickerDialog
import android.graphics.Color
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import androidx.lifecycle.ViewModelProvider
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityDiaperBinding
import com.ajolla.lactomama.model.StatusDataClass
import com.ajolla.lactomama.viewModel.Viewmodel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date
import java.util.Locale

class DiaperActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDiaperBinding
    private lateinit var diaperStatus: String
    private val calendar = Calendar.getInstance()
    private lateinit var viewModel: Viewmodel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDiaperBinding.inflate(layoutInflater)
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[Viewmodel::class.java]

        binding.diaperTimeTextview.setOnClickListener {
            showTimePickerDialog(binding.diaperTimeTextview)
        }

        binding.statusWet.setOnClickListener {
            setDiaperStatusSelection(
                R.drawable.icon_wet_selected,
                R.drawable.icon_dirty_unselected,
                R.drawable.icon_mixed_unselected,
                R.drawable.icon_dry_unselected,
                Color.parseColor("#4625C3"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2")
            )
            diaperStatus = "Wet"
        }

        binding.statusDirty.setOnClickListener {
            setDiaperStatusSelection(
                R.drawable.icon_wet_unselected,
                R.drawable.icon_dirty_selected,
                R.drawable.icon_mixed_unselected,
                R.drawable.icon_dry_unselected,
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#4625C3"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2")
            )
            diaperStatus = "Dirty"
        }

        binding.statusMixed.setOnClickListener {
            setDiaperStatusSelection(
                R.drawable.icon_wet_unselected,
                R.drawable.icon_dirty_unselected,
                R.drawable.icon_mixed_selected,
                R.drawable.icon_dry_unselected,
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#4625C3"),
                Color.parseColor("#C2C2C2")
            )
            diaperStatus = "Mixed"
        }

        binding.statDry.setOnClickListener {
            setDiaperStatusSelection(
                R.drawable.icon_wet_unselected,
                R.drawable.icon_dirty_unselected,
                R.drawable.icon_mixed_unselected,
                R.drawable.icon_dry_selected,
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#C2C2C2"),
                Color.parseColor("#4625C3")
            )
            diaperStatus = "Dry"
        }

        binding.dipaerNextButton.setOnClickListener {
            saveDiaperData()
            finish()
            println("Diaper saved")
        }

        binding.diaperBackButton.setOnClickListener {
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

    private fun setDiaperStatusSelection(
        wetImageResource: Int,
        dirtyImageResource: Int,
        mixedImageResource: Int,
        dryImageResource: Int,
        wetTextColor: Int,
        dirtyTextColor: Int,
        mixedTextColor: Int,
        dryTextColor: Int
    ) {
        binding.statusWet.setTextColor(wetTextColor)
        binding.statusDirty.setTextColor(dirtyTextColor)
        binding.statusMixed.setTextColor(mixedTextColor)
        binding.statDry.setTextColor(dryTextColor)

        binding.imageWet.setImageResource(wetImageResource)
        binding.imageDirty.setImageResource(dirtyImageResource)
        binding.imageMixed.setImageResource(mixedImageResource)
        binding.imageDry.setImageResource(dryImageResource)
    }

    private fun saveDiaperData() {
        val diaperTime = binding.diaperTimeTextview.text.toString()
        val diaperNote = binding.dipaerNote.text.toString()

        val diaper = StatusDataClass(
            diaperTime = diaperTime,
            diaperNote = diaperNote,
            diaperStatus = diaperStatus,
            today = date(),
            hour = hour()
        )
        viewModel.insertSleep(diaper)
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
        val time = dateFormat.format(currentDate)
        println(time)
        return time
    }
}