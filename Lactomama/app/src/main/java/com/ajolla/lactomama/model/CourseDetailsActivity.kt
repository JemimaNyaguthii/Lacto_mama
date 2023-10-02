package com.ajolla.lactomama.model

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.ajolla.lactomama.databinding.ActivityCourseDetailsBinding
import com.ajolla.lactomama.mother.PaymentActivity
import com.ajolla.lactomama.mother.cart.Course
import java.io.Serializable

class CourseDetailsActivity : AppCompatActivity() {
    lateinit var binding: ActivityCourseDetailsBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCourseDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btnPay.setOnClickListener {
            val selectedCourse = getCurrentCourse()
            if (selectedCourse != null) {
                addCourseToCart(selectedCourse)
                Toast.makeText(this, "Course added to cart", Toast.LENGTH_SHORT).show()
            }

            val paymentIntent = Intent(this, PaymentActivity::class.java)
            startActivity(paymentIntent)
        }
    }

    private fun getCurrentCourse(): Course? {
        val image = binding.ivCover.tag as? String
        val name = binding.tvTitleDescription.text.toString()
        val price = binding.tvPric.text.toString()

        return if (image != null && name.isNotEmpty() && price.isNotEmpty()) {
            Course(image, price.toDouble(), name)
        } else {
            null
        }
    }

    private fun addCourseToCart(course: Course) {
        val paymentIntent = Intent(this, PaymentActivity::class.java)
        paymentIntent.putExtra("course", course as Serializable)
        startActivity(paymentIntent)
    }
}