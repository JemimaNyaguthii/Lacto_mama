package com.ajolla.lactomama.ui


import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityEditProfileBinding
import com.ajolla.lactomama.databinding.ActivityMotherEditProfileBinding

class MotherEditProfile : AppCompatActivity() {
    lateinit var binding: ActivityMotherEditProfileBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMotherEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }
    override fun onResume() {
        super.onResume()
        binding.btnCancel.setOnClickListener {
            val intent = Intent(this,MotherProfile::class.java)
            startActivity(intent)
        }
        binding.btnSaveEdits.setOnClickListener {
            val intent = Intent(this,MotherEditProfile::class.java)
            startActivity(intent)
        }
    }
}
