package com.ajolla.lactomama.mother.cart

import android.content.Intent
import android.os.Bundle
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.lifecycle.ViewModelProvider
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
import com.ajolla.lactomama.databinding.ActivityShoppingCartBinding
import com.ajolla.lactomama.mother.FragmentAll
import com.ajolla.lactomama.viewModel.ShoppingCartViewModel
import com.ajolla.lactomama.viewModel.ShoppingCartViewModelFactory
import com.ajolla.lactomama.mother.cart.ShoppingCartAdapter
import com.ajolla.lactomama.mother.cart.CartItem
import com.ajolla.lactomama.ui.HomeFragment
import com.ajolla.lactomama.ui.PaymentProcedure

class ShoppingCartActivity : AppCompatActivity() {
    private lateinit var binding: ActivityShoppingCartBinding
    private lateinit var adapter: ShoppingCartAdapter
    private lateinit var viewModel: ShoppingCartViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityShoppingCartBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val retrofit = NewClient.getRetrofitClient()
        val apiInterface = retrofit.create(ApiInterface::class.java)

        val viewModelFactory = ShoppingCartViewModelFactory(apiInterface)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel::class.java)

        binding.shoppingCartRecyclerView.layoutManager = LinearLayoutManager(this)
        adapter = ShoppingCartAdapter(this,
            onAddItemClick = { cartItem ->
                val productToUpdate = cartItem.product
                val updatedCartItem = cartItem.copy(quantity = cartItem.quantity + 1)

                val cartItems = ShoppingCart.getCart(this)

                val itemIndex = cartItems.indexOfFirst { it.product == productToUpdate }

                if (itemIndex != -1) {
                    cartItems[itemIndex] = updatedCartItem // Update the item in the cart
                }

                ShoppingCart.saveCart(cartItems, this) // Save the updated cart to shared preferences

                viewModel.updateCartItems(cartItems) // Update the ViewModel

                // Notify the adapter that the data has changed
                adapter.updateCartItems(cartItems)

                // Update the total price
                updateTotalPrice(cartItems)
            },
            onRemoveItemClick = { cartItem ->
                val productToUpdate = cartItem.product
                val cartItems = ShoppingCart.getCart(this)

                val itemIndex = cartItems.indexOfFirst { it.product == productToUpdate }

                if (itemIndex != -1) {
                    val currentQuantity = cartItems[itemIndex].quantity
                    if (currentQuantity > 1) {
                        // Decrease the quantity by 1
                        cartItems[itemIndex] = cartItems[itemIndex].copy(quantity = currentQuantity - 1)
                    } else {
                        // If the quantity is 1, remove the item from the cart
                        cartItems.removeAt(itemIndex)
                    }

                    ShoppingCart.saveCart(cartItems, this) // Save the updated cart to shared preferences
                    viewModel.updateCartItems(cartItems) // Update the ViewModel
                    adapter.updateCartItems(cartItems) // Notify the adapter that the data has changed
                    updateTotalPrice(cartItems) // Update the total price
                }
            }
        )

        binding.shoppingCartRecyclerView.adapter = adapter

        // Fetch and display cart items
        val cartItems = ShoppingCart.getCart(this)
        adapter.updateCartItems(cartItems)
        updateTotalPrice(cartItems)

        // Observe the cart items in the ViewModel
        viewModel.getCartItems().observe(this) { cartItems ->
            // Update the total price
            updateTotalPrice(cartItems)
        }

        binding.btnCheckout.setOnClickListener {
            val intent = Intent(this, PaymentProcedure::class.java)
            startActivity(intent)
        }
        binding.ivBackHome.setOnClickListener {
            val intent = Intent( this, HomeFragment::class.java)
            startActivity(intent)
        }
    }

    private fun updateTotalPrice(cartItems: List<CartItem>) {
        var totalPrice = 0.0
        for (cartItem in cartItems) {
            val price = cartItem.product.price.toDouble()
            totalPrice += cartItem.quantity * price
        }
        binding.totalPrice.text = String.format("ksh%.2f", totalPrice)
    }

    // ... (remaining methods)
}
