package com.ajolla.lactomama.mother.cart

import android.content.Intent
import android.graphics.PorterDuff
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContextCompat
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
import com.ajolla.lactomama.databinding.ActivityShoppingCartBinding
import com.ajolla.lactomama.ui.PaymentProcedure
import com.ajolla.lactomama.viewModel.ShoppingCartViewModel
import com.ajolla.lactomama.viewModel.ShoppingCartViewModelFactory

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCartBinding
    private lateinit var adapter: ShoppingCartAdapter
    private lateinit var viewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayShowHomeEnabled(true)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)
        val upArrow = ContextCompat.getDrawable(this, R.drawable.baseline_arrow_back_24)
        upArrow?.setColorFilter(ContextCompat.getColor(this, R.color.white), PorterDuff.Mode.SRC_ATOP)
        supportActionBar?.setHomeAsUpIndicator(upArrow)

        val retrofit = NewClient.getRetrofitClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        val viewModelFactory = ShoppingCartViewModelFactory(apiInterface)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel::class.java)

        viewModel.fetchCartItems()

        viewModel.getCartItems().observe(this) { cartItems ->
            adapter.submitList(cartItems)

            var totalPrice = 0.0
            for (cartItem in cartItems) {
                val price = cartItem.product.price.toDouble()
                totalPrice += cartItem.quantity * price
            }
            binding.totalPrice.text = String.format("ksh%.2f", totalPrice)
        }

        adapter = ShoppingCartAdapter(this,
            onAddItemClick = { cartItem ->
                // Handle "add" button click
                val product = cartItem.product
                product?.let {
                    // Handle adding to cart
                    // You should implement this in your adapter or ViewModel.
                    // Example: viewModel.addToCart(cartItem)
                }
            },
            onRemoveItemClick = { cartItem ->
                // Handle "remove" button click
                val product = cartItem.product
                product?.let {
                }
            }
        )
        binding.shoppingCartRecyclerView.adapter = adapter
        binding.shoppingCartRecyclerView.layoutManager = LinearLayoutManager(this)
    }

    override fun onResume() {
        super.onResume()
        binding.btnCheckout.setOnClickListener {
        val intent = Intent(this, PaymentProcedure::class.java)

            startActivity(intent)
}


        }
    }

