package com.ajolla.lactomama.ui.status

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityChildHomePageBinding
import com.ajolla.lactomama.viewModel.Viewmodel
import kotlinx.coroutines.launch
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.time.temporal.ChronoUnit

class ChildHomePageActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChildHomePageBinding  // Corrected binding type
    private lateinit var viewModel: Viewmodel
    var value: ByteArray? = null

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChildHomePageBinding.inflate(layoutInflater)  // Corrected binding initialization
        setContentView(binding.root)

        viewModel = ViewModelProvider(this)[Viewmodel::class.java]

        lifecycleScope.launch {
            println("data" + viewModel.getData())

            viewModel.getData().forEach {
                binding.babyName.text = it.babyFullName
                val dateString = it.birthDate
                val formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy")
                val date = LocalDate.parse(dateString, formatter)

                var now = LocalDate.now()

                val months = ChronoUnit.MONTHS.between(date, now)
                val days = ChronoUnit.DAYS.between(date.plusMonths(months), now)

                binding.babyMonths.text = "$months Months $days Days"
                println("time since the given date: $months months $days days")

                value = it.babyProfileImage
                if (value == null) {
                    binding.imageView7.setImageResource(R.drawable.btn_addphoto)
                }
                if (value != null) {
                    var bitmap = value?.let {
                        BitmapFactory.decodeByteArray(value, 0, it.size)
                    }
                    binding.imageView7.setImageBitmap(bitmap)
                }
            }
        }

        binding.imageviewFeeding.setOnClickListener {
            val intent = Intent(this, FeedingActivity::class.java)
            startActivity(intent)
        }

        binding.imageviewDiaperchange.setOnClickListener {
            val intent = Intent(this, DiaperActivity::class.java)
            startActivity(intent)        }

        binding.imageviewSleep.setOnClickListener {
            val intent = Intent(this, SleepActivity::class.java)
            startActivity(intent)        }

        binding.homeCalender.setOnClickListener {
            val intent = Intent(this, CalendarActivity::class.java)
            startActivity(intent)
        }

        binding.babyEditProfile.setOnClickListener {
            val intent = Intent(this, LoginFragment::class.java)
            startActivity(intent)        }
    }
}