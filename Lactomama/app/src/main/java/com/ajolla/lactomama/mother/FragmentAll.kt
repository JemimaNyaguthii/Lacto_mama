package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ajolla.lactomama.databinding.FragmentAllBinding
import com.ajolla.lactomama.mother.cart.CartItem
import com.ajolla.lactomama.mother.cart.ShoppingCart
import com.ajolla.lactomama.mother.cart.ShoppingCartActivity
import com.ajolla.lactomama.mother.cart.ShoppingCartAdapter
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
import com.ajolla.lactomama.model.CoursesRvAdapter
import com.ajolla.lactomama.model.Product
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.CourseAdapter
import com.ajolla.lactomama.viewModel.ShoppingCartViewModel
import com.ajolla.lactomama.viewModel.ShoppingCartViewModelFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAll : Fragment() {
    private lateinit var binding: FragmentAllBinding
    private lateinit var adapter: ShoppingCartAdapter
    private lateinit var viewModel: ShoppingCartViewModel
    private val apiService: ApiInterface = NewClient.getRetrofitClient().create(ApiInterface::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentAllBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.basketButton.setOnClickListener {
            val intent = Intent(requireContext(), ShoppingCartActivity::class.java)
            startActivity(intent)
        }

        val cartItems: List<CartItem> = ShoppingCart.getCart(requireContext())
        for (cartItem in cartItems) {
            Log.d("CartItem", "${cartItem.product.id} - ${cartItem.product.name}")
        }
        val viewModelFactory = ShoppingCartViewModelFactory(apiService)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ShoppingCartViewModel::class.java)

        // Create a click listener for the adapter
        adapter = ShoppingCartAdapter(requireContext(),
            onAddItemClick = { cartItem ->
                // Handle "add" button click
                ShoppingCart.addItem(cartItem.product, requireContext())
                adapter.notifyDataSetChanged()
                // Update any relevant UI or ViewModel actions
            },
            onRemoveItemClick = { cartItem ->
                // Handle "remove" button click
                ShoppingCart.removeFromCart(cartItem.product, requireContext())
                adapter.notifyDataSetChanged()
                // Update any relevant UI or ViewModel actions
            }
        )

        binding.productsRecyclerview.adapter = adapter
        binding.productsRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)
        var totalPrice = 0.0

        for (cartItem in cartItems) {
            val price = cartItem.product.price.toDouble()
            totalPrice += cartItem.quantity * price
        }

        binding.showCart.tag = String.format("ksh%.2f", totalPrice)

        // Fetch and display courses
        fetchAndDisplayCourses()

        return view
    }

    private fun fetchAndDisplayCourses() {

        getCourses()
    }

    private fun getCourses() {
        showLoading()

        apiService.getCourses().enqueue(object : Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                // Handle the failure, display an error message, and hide loading progress
                val errorMessage = t.message ?: "An error occurred"
                Log.d("Data error", errorMessage)
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                hideLoading()
            }

            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                // Handle the response and update the UI
                hideLoading()
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()
                    val courseAdapter = CoursesRvAdapter(requireContext(), courses) { course ->

                    }
                    binding.productsRecyclerview.adapter = courseAdapter
                } else {
                    val errorMessage = "Request was not successful. Status code: ${response.code()}"
                    Log.d("Data error", errorMessage)
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showLoading() {
    }

    private fun hideLoading() {
    }
}
