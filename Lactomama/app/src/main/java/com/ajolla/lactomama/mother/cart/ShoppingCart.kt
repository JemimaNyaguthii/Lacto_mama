package com.ajolla.lactomama.mother.cart

import android.content.Context
import com.ajolla.lactomama.model.Product
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class ShoppingCart {
    companion object {
        private const val CART_PREFS = "cart_prefs"

        fun addItem(product: Product, context: Context) {
            val cart = getCart(context)

            val targetItem = cart.singleOrNull { it.product.id == product.id }

            if (targetItem == null) {
                val cartItem = CartItem(product)
                cartItem.quantity++
                cart.add(cartItem)
            } else {
                targetItem.quantity++
            }

            saveCart(cart, context)
        }

        fun removeFromCart(product: Product, context: Context) {
            val cart = getCart(context)

            val targetItem = cart.singleOrNull { it.product.id == product.id }

            if (targetItem != null) {
                if (targetItem.quantity > 0) {
                    targetItem.quantity--
                } else {
                    cart.remove(targetItem)
                }

                saveCart(cart, context)
            }
        }

        fun getCart(context: Context): MutableList<CartItem> {
            val prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE)
            val cartJson = prefs.getString("cart", "[]")
            return Gson().fromJson(cartJson, object : TypeToken<MutableList<CartItem>>() {}.type)
        }

        fun saveCart(cart: MutableList<CartItem>, context: Context) {
            val prefs = context.getSharedPreferences(CART_PREFS, Context.MODE_PRIVATE)
            val editor = prefs.edit()
            val cartJson = Gson().toJson(cart)
            editor.putString("cart", cartJson)
            editor.apply()
        }

        fun getShoppingCartSize(context: Context): Int {
            var cartSize = 0
            getCart(context).forEach {
                cartSize += it.quantity
            }
            return cartSize
        }
    }
}