package com.ajolla.lactomama.ui

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.ActivityPaymentBinding
import com.ajolla.lactomama.ui.cart.CartRvAdapter
import com.ajolla.lactomama.ui.cart.Course

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    private lateinit var rvCourses: RecyclerView
    private val cart = mutableListOf<Course>()
    private lateinit var adapter: CartRvAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

        adapter = CartRvAdapter(cart)
        binding.rvCourses.layoutManager = LinearLayoutManager(this)
        binding.rvCourses.adapter = adapter



        val selectedCourse = intent.getSerializableExtra("course") as? Course
        if (selectedCourse != null) {
            cart.add(selectedCourse)
            adapter.notifyDataSetChanged()
        }

        binding.btnPayCourse.setOnClickListener {
            val intent = Intent(this, PaymentProcedure::class.java)
            startActivity(intent)
        }
    }
}
