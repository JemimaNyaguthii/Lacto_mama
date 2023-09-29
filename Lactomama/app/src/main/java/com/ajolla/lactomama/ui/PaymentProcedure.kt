package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.PaymentSuccessful
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityPaymentProcedureBinding

class PaymentProcedure : AppCompatActivity() {
    lateinit var binding: ActivityPaymentProcedureBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityPaymentProcedureBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.btnFinish.setOnClickListener {
            val paymentIntent = Intent(this,PaymentSuccessful::class.java)
            startActivity(paymentIntent)
        }
    }
}