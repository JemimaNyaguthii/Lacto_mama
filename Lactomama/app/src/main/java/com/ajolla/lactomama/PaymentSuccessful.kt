package com.ajolla.lactomama

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityPaymentSuccessfulBinding

class PaymentSuccessful : AppCompatActivity() {
    lateinit var binding:ActivityPaymentSuccessfulBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentSuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}