package com.ajolla.lactomama

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import android.widget.Toast
import com.ajolla.lactomama.databinding.ActivityPaymentSuccessfulBinding
import com.ajolla.lactomama.ui.PaymentActivity

class PaymentSuccessful : AppCompatActivity() {
    lateinit var binding:ActivityPaymentSuccessfulBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentSuccessfulBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }
}