package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityAppointmentsBinding
import com.ajolla.lactomama.databinding.ActivityListcourseBinding

class Listcourse : AppCompatActivity() {
    lateinit var binding: ActivityListcourseBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityListcourseBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.imvback2.setOnClickListener {
            val  intent = Intent(this,ProfileFragment::class.java)
            startActivity(intent)
        }

    }
}