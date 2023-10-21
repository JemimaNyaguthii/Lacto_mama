package com.ajolla.lactomama.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ajolla.lactomama.mother.cart.CartItem
import com.ajolla.lactomama.api.ApiInterface
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ShoppingCartViewModel(private val apiInterface: ApiInterface) : ViewModel() {
    private val cartItemsLiveData = MutableLiveData<List<CartItem>>()

    init {
        // You can initialize your ViewModel here if needed.
        // For example, you can set default values or perform any necessary setup.
    }

    fun fetchCartItems() {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val products = apiInterface.getProducts().execute().body()
                val cartItems = products?.map { CartItem(it) } ?: emptyList()

                // Update LiveData with the new cart items
                cartItemsLiveData.postValue(cartItems)
            } catch (e: Exception) {
                // Handle the error, e.g., show an error message or retry
                // Update LiveData with an empty list in case of an error
                cartItemsLiveData.postValue(emptyList())
            }
        }
    }

    fun getCartItems(): LiveData<List<CartItem>> {
        return cartItemsLiveData
    }

    // No need to implement addToCart and removeFromCart here.
    // These methods should be handled in your CartAdapter or CartRepository.
}
