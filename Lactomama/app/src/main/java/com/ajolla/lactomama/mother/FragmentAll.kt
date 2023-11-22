package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.ajolla.lactomama.databinding.FragmentAllBinding
import com.ajolla.lactomama.mother.cart.CartItem
import com.ajolla.lactomama.mother.cart.ShoppingCart
import com.ajolla.lactomama.mother.cart.ShoppingCartActivity
import com.ajolla.lactomama.mother.cart.ShoppingCartAdapter
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
import com.ajolla.lactomama.model.Product
import com.ajolla.lactomama.mother.cart.Course
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class FragmentAll : Fragment() {
    private lateinit var binding: FragmentAllBinding
    private lateinit var adapter: ShoppingCartAdapter
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

        // Create an instance of your ShoppingCartAdapter
        adapter = ShoppingCartAdapter(requireContext(),
            onAddItemClick = { cartItem ->
                val cartItemToAdd = CartItem(cartItem.product) // Create a CartItem with the product
                ShoppingCart.addItem(cartItemToAdd, requireContext())
                adapter.notifyDataSetChanged()
            },
            onRemoveItemClick = { /* Do nothing when removing items for this fragment */ }
        )


        binding.productsRecyclerview.adapter = adapter
        binding.productsRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

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
                val errorMessage = t.message ?: "An error occurred"
                Log.d("Data error", errorMessage)
                Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                hideLoading()
            }

            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                hideLoading()
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()

                    // Convert the Course items to CartItem items
                    val cartItems = courses.mapNotNull { course ->
                        // Handle null values with safe calls or default values
                        val product = Product(
                            id = course.id ?: 0,  // Default value for id
                            title = course.title ?: "",  // Default value for title
                            itemsName = course.itemsName ?: "",  // Default value for itemsName
                            numberOfItems = course.numberOfItems?.toString() ?: "0",  // Handle null numberOfItems
                            price = course.price ?: 0.0,  // Default value for price
                            description = course.description ?: "",  // Default value for description
                            discount = course.discount ?: 0.0  // Default value for discount
                        )

                        CartItem(product, quantity = 1)
                    }

                    adapter.updateCartItems(cartItems)  // Update the adapter with the fetched cart items
                } else {
                          // Set an OnClickListener for the Lactation Specialist button
  val errorMessage = "Request was not successful. Status code: ${response.code()}"
                    Log.d("Data error", errorMessage)
                    Toast.makeText(requireContext(), errorMessage, Toast.LENGTH_SHORT).show()
                }
            }
        })
    }

    private fun showLoading() {
        // Implement loading state if needed
    }

    private fun hideLoading() {
        // Hide loading state if needed
    }
}
