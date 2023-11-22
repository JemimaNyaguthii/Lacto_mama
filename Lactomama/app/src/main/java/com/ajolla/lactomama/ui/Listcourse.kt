package com.ajolla.lactomama.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import android.widget.Toast
import com.ajolla.lactomama.databinding.ActivityListcourseBinding
import com.ajolla.lactomama.mother.cart.CartItem
import com.ajolla.lactomama.mother.cart.ShoppingCartAdapter
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.api.NewClient
import com.ajolla.lactomama.model.Product
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import android.content.Intent
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.ui.ProfileFragment

class Listcourse : AppCompatActivity() {
    private lateinit var binding: ActivityListcourseBinding
    private lateinit var adapter: ShoppingCartAdapter
    private val apiService: ApiInterface = NewClient.getRetrofitClient().create(ApiInterface::class.java)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityListcourseBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.imvback2.setOnClickListener {
            val intent = Intent(this, ProfileFragment::class.java)
            startActivity(intent)
        }

        // Create an instance of your ShoppingCartAdapter
        adapter = ShoppingCartAdapter(this,
            onAddItemClick = { cartItem ->
                val cartItemToAdd = CartItem(cartItem.product) // Create a CartItem with the product
            },
            onRemoveItemClick = { /* Handle removing items from the shopping cart if needed */ }
        )

        binding.productsRecyclerview.adapter = adapter
        binding.productsRecyclerview.layoutManager = StaggeredGridLayoutManager(2, StaggeredGridLayoutManager.VERTICAL)

        fetchAndDisplayCourses()
    }

    private fun fetchAndDisplayCourses() {
        getCourses()
    }

    private fun getCourses() {
        showLoading()

        apiService.getCourses().enqueue(object : Callback<List<Course>> {
            override fun onFailure(call: Call<List<Course>>, t: Throwable) {
                val errorMessage = t.message ?: "An error occurred"
                Toast.makeText(this@Listcourse, errorMessage, Toast.LENGTH_SHORT).show()
                hideLoading()
            }

            override fun onResponse(call: Call<List<Course>>, response: Response<List<Course>>) {
                hideLoading()
                if (response.isSuccessful) {
                    val courses = response.body() ?: emptyList()

                    // Convert the Course items to CartItem items
                    val cartItems = courses.mapNotNull { course ->
                        val product = Product(
                            id = course.id ?: 0,
                            title = course.title ?: "",
                            itemsName = course.itemsName ?: "",
                            numberOfItems = course.numberOfItems?.toString() ?: "0",
                            price = course.price ?: 0.0,
                            description = course.description ?: "",
                            discount = course.discount ?: 0.0
                        )

                        CartItem(product, quantity = 1)
                    }

                    adapter.updateCartItems(cartItems)
                } else {
                    val errorMessage = "Request was not successful. Status code: ${response.code()}"
                    Toast.makeText(this@Listcourse, errorMessage, Toast.LENGTH_SHORT).show()
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
