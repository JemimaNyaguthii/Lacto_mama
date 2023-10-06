package com.ajolla.lactomama.model

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ajolla.lactomama.databinding.ActivityCourseDetailsBinding
import com.ajolla.lactomama.mother.FragmentAll
import com.ajolla.lactomama.mother.PaymentActivity
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.PaymentProcedure
import java.io.Serializable

class CourseDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPay.setOnClickListener {

            val paymentIntent = Intent(this, PaymentActivity::class.java)
            startActivity(paymentIntent)
        }
        binding.ivNotification.setOnClickListener {
            val coursesIntent=Intent(this, FragmentAll::class.java)
            startActivity(coursesIntent)
        }
    }

    private fun addCourseToCart(course: Course) {
        val paymentIntent = Intent(this, PaymentActivity::class.java)
        paymentIntent.putExtra("course", course as Serializable)
        startActivity(paymentIntent)
    }
}