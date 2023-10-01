package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivitySuccessBinding
import com.ajolla.lactomama.ui.home.UploadCoursesFragment

class Success : AppCompatActivity() {
    lateinit var binding: ActivitySuccessBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivitySuccessBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btncontinue.setOnClickListener {
            val intent = Intent( this, UploadCoursesFragment::class.java)
            startActivity(intent)
        }

    }
}