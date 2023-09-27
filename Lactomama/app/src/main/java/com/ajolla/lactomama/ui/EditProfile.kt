package com.ajolla.lactomama.ui
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Button
import android.content.Intent
import com.ajolla.lactomama.R
import com.ajolla.lactomama.ui.LactationistProfile

class EditProfile: AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_profile)

        val btnCancel = findViewById<Button>(R.id.btncancel)
        val btnSave = findViewById<Button>(R.id.btnsavee)

        btnCancel.setOnClickListener {

            finish()         }

        btnSave.setOnClickListener {
            val intent = Intent(this, EditProfile::class.java)
            startActivity(intent)
        }
    }
}
