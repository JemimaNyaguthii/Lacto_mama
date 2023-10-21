package com.ajolla.lactomama.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.ProductRawBinding
import com.squareup.picasso.Picasso


class ProductAdapter(
    private val context: Context,
    private val products: List<Product>,
    private val onProductClick: (Product) -> Unit
) : RecyclerView.Adapter<ProductAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = ProductRawBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int = products.size

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindProduct(products[position])
    }

    inner class ViewHolder(private val binding: ProductRawBinding) :
        RecyclerView.ViewHolder(binding.root) {

        fun bindProduct(product: Product) {
            binding.productName.text = product.name
            binding.productPrice.text = "$${product.price}"



            binding.root.setOnClickListener { onProductClick(product) }
        }
    }
}