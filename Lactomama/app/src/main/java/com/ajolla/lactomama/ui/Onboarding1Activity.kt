package com.ajolla.lactomama.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityOnboarding1Binding

class Onboarding1Activity : AppCompatActivity() {
    lateinit var binding: ActivityOnboarding1Binding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarding1Binding.inflate(layoutInflater)
        setContentView(binding.root)
//        Handler(Looper.getMainLooper()).postDelayed({
//            startActivity(Intent(this,Onboarding2Activity::class.java))
//            finish()
//        },2000)
    }

    override fun onResume() {
        super.onResume()
    }
}