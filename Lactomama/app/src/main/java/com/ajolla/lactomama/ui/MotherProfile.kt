package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityMotherProfileBinding


class MotherProfile : AppCompatActivity() {
    lateinit var binding: ActivityMotherProfileBinding
   override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
//    binding.tvSignUp.setOnClickListener {
//        val intent = Intent(this,MotherSignUp::class.java)
//        startActivity(intent)
//    }




    override fun onResume() {
        super.onResume()

        binding.ivArrow.setOnClickListener {
            val intent = Intent(this, MotherSignUp::class.java)
            startActivity(intent)
        }
        binding.tvEditMotherProfile.setOnClickListener {
            val intent = Intent(this,MotherEditProfile::class.java)
            startActivity(intent)
        }
    }


}