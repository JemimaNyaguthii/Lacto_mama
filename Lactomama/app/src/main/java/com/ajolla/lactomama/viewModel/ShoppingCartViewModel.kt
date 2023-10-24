package com.ajolla.lactomama.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.ajolla.lactomama.api.ApiInterface
import com.ajolla.lactomama.mother.cart.CartItem

class ShoppingCartViewModel(private val apiInterface: ApiInterface) : ViewModel() {

    private val cartItemsLiveData = MutableLiveData<List<CartItem>>()

    // Initialize the ViewModel, if needed

    fun getCartItems(): LiveData<List<CartItem>> {
        return cartItemsLiveData
    }

    // No need for a fetchCartItems function here since the data is already fetched in ShoppingCartActivity.

    // Update the cart items in the ViewModel
    fun updateCartItems(cartItems: List<CartItem>) {
        cartItemsLiveData.postValue(cartItems)
    }
}
