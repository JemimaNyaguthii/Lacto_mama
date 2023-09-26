package com.ajolla.lactomama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.ajolla.lactomama.databinding.ActivityOnboarding3Binding

class Onboarding3Activity : AppCompatActivity() {
    lateinit var binding: ActivityOnboarding3Binding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityOnboarding3Binding.inflate(layoutInflater)
        setContentView(binding.root)

        Handler(Looper.getMainLooper()).postDelayed({
       startActivity(Intent(this,UserJourneyActivity::class.java))
            finish()
        },3000)
    }
}
