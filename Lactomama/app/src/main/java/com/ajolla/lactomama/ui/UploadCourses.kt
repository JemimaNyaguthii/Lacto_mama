package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
<<<<<<< HEAD:Lactomama/app/src/main/java/com/ajolla/lactomama/MainActivity.kt
import android.os.Handler
import com.ajolla.lactomama.databinding.ActivityMainBinding
import com.ajolla.lactomama.ui.home.HomePageActivity

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Handler().postDelayed({
            val intent = Intent(this@MainActivity, HomePageActivity::class.java)
            intent.flags = Intent.FLAG_ACTIVITY_CLEAR_TASK
            startActivity(intent)
            finish()
        },1000)
=======
import com.ajolla.lactomama.R

class UploadCourses : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_upload_courses)
>>>>>>> 18733763552c09b12d3d06fd4d22c6595161afba:Lactomama/app/src/main/java/com/ajolla/lactomama/ui/UploadCourses.kt
    }
}

//supportActionBar?.hide()