package com.ajolla.lactomama.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityEditProfileBinding

class EditProfile: AppCompatActivity() {
    lateinit var binding: ActivityEditProfileBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityEditProfileBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val btnCancel = findViewById<Button>(R.id.btncancel)
        val btnSave = findViewById<Button>(R.id.btnsavee)

        btnCancel.setOnClickListener {

            finish()         }

        btnSave.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }

        binding.imvback31.setOnClickListener {
            val intent = Intent(this,ProfileFragment::class.java)
            startActivity(intent)
        }
    }
}
