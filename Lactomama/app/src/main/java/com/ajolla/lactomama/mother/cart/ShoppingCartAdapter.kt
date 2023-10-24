package com.ajolla.lactomama.mother.cart

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.CartListItemBinding

class ShoppingCartAdapter(
    private val context: Context,
    private val onAddItemClick: (CartItem) -> Unit,
    private val onRemoveItemClick: (CartItem) -> Unit
) : ListAdapter<CartItem, ShoppingCartAdapter.ViewHolder>(CartItemsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val cartItem = getItem(position)
        holder.bindItem(cartItem)
        holder.bindAddRemoveListeners(cartItem)
    }

    inner class ViewHolder(private val binding: CartListItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bindItem(cartItem: CartItem) {
            binding.productName.text = cartItem.product.title
            binding.productPrice.text = "ksh${cartItem.product.price}"
            binding.productQuantity.text = cartItem.quantity.toString()
            binding.productDescription.text = cartItem.product.description
        }
        fun bindAddRemoveListeners(cartItem: CartItem) {
            binding.addToCart.setOnClickListener {
                onAddItemClick(cartItem)
            }
            binding.removeItem.setOnClickListener {
                onRemoveItemClick(cartItem)
            }
        }
    }

    private class CartItemsDiffCallback : DiffUtil.ItemCallback<CartItem>() {
        override fun areItemsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem.product.id == newItem.product.id
        }

        override fun areContentsTheSame(oldItem: CartItem, newItem: CartItem): Boolean {
            return oldItem == newItem
        }
    }

    fun updateCartItems(cartItems: List<CartItem>) {
        submitList(cartItems)
    }
}