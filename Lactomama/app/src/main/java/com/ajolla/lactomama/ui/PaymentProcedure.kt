package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import com.ajolla.lactomama.PaymentSuccessful
import com.ajolla.lactomama.databinding.ActivityPaymentProcedureBinding
import com.ajolla.lactomama.mother.FragmentAll
import com.ajolla.lactomama.mother.cart.ShoppingCartActivity
import com.example.amalimobile.ui.home.donation.DarajaViewModel

class PaymentProcedure : AppCompatActivity() {
    lateinit var binding: ActivityPaymentProcedureBinding
    lateinit var darajaViewModel: DarajaViewModel
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentProcedureBinding.inflate(layoutInflater)
        darajaViewModel = ViewModelProvider(this)[DarajaViewModel::class.java]
        setContentView(binding.root)

        binding.btnFinish.setOnClickListener {
            if (binding.etPhoneNumber.text.isNullOrBlank() || binding.etAmount.text.isNullOrBlank()) {
                if (binding.etPhoneNumber.text.isNullOrBlank()) {
                    binding.etPhoneNumber.error = "Please enter a phone number"
                }
                if (binding.etAmount.text.isNullOrBlank()) {
                    binding.etAmount.error = "Please enter an amount"
                }
            } else {
                val phone: String = binding.etPhoneNumber.text.toString()
                val amount = "10"
                val intent = Intent(this, PaymentSuccessful::class.java)
                startActivity(intent)
            }
        }
    }
        override fun onResume() {
            super.onResume()
            binding.ivBackShopping.setOnClickListener {
                val intent = Intent( this,ShoppingCartActivity::class.java)
                startActivity(intent)
            }
        }

}