package com.ajolla.lactomama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityAppointmentsBinding
import com.ajolla.lactomama.ui.ProfileFragment

class Appointments : AppCompatActivity() {
    lateinit var binding: ActivityAppointmentsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)




        binding.imvback1.setOnClickListener {
            val intent =Intent( this,ProfileFragment::class.java)
            startActivity(intent)
        }


    }
}
