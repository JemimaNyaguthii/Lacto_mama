package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityUserJourneyBinding
import com.ajolla.lactomama.mother.HomePageActivity

class UserJourneyActivity : AppCompatActivity() {
    lateinit var binding: ActivityUserJourneyBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityUserJourneyBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val lactationButton = findViewById<Button>(R.id.lactationButton)
        val motherButton = findViewById<Button>(R.id.motherButton)
        lactationButton.setOnClickListener {
            val intent = Intent(this,LactationistLogin::class.java)
            startActivity(intent)
        }
        // Set an OnClickListener for the Mother button
        motherButton.setOnClickListener {
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }
    }
}
//mainactivity