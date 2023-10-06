package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.ActivityPaymentBinding
import com.ajolla.lactomama.ui.PaymentProcedure
import com.ajolla.lactomama.mother.cart.CartRvAdapter
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.viewModel.CartViewmodel

class PaymentActivity : AppCompatActivity() {
    lateinit var binding: ActivityPaymentBinding
    val cartViewmodel: CartViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPaymentBinding.inflate(layoutInflater)
        setContentView(binding.root)

            binding.btnCheckout.setOnClickListener {
                val intent = Intent(this, PaymentProcedure::class.java)
                startActivity(intent)
            }
        }

        override fun onResume() {
            super.onResume()
            cartViewmodel.fetchCart()
            cartViewmodel.cartLiveData.observe(this,Observer {cartList ->
                Toast.makeText(baseContext, "Fetched ${cartList?.size} courses", Toast.LENGTH_LONG)
                    .show()
                binding.rvCourses.layoutManager = GridLayoutManager(this@PaymentActivity,2)
                binding.rvCourses.adapter = CartRvAdapter(cartList)
            })
            cartViewmodel.errorLiveData.observe(this,Observer{error ->
                Toast.makeText(baseContext, error, Toast.LENGTH_LONG).show()
            })
        }

}
