package com.ajolla.lactomama.ui




import android.content.Intent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.SuccessScreenBinding


class SuceessScreen : AppCompatActivity() {
    lateinit var binding: SuccessScreenBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.success_screen)
        binding = SuccessScreenBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.button.setOnClickListener {
            val intent = Intent (this, LactationistHome::class.java)
            startActivity(intent)
        }





    }



}

