package com.ajolla.lactomama.mother.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.CartListItemBinding
import com.ajolla.lactomama.mother.CoursesData

class CartRvAdapter(val courses:List<Course>) : RecyclerView.Adapter<CartRvAdapter.CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val currentCourse = courses.get(position)
        val binding = holder.binding

        binding.tvIdd.text = currentCourse.id.toString()
        binding.tvDiscount.text=currentCourse.discount.toString()
        binding.tvItemsName.text=currentCourse.itemsName
        binding.tvNumberOfItems.text=currentCourse.numberOfItems
        binding.tvDescriptionn.text=currentCourse.description
        binding.tvPrice.text=currentCourse.price.toString()
    }
    override fun getItemCount(): Int {
        return courses.size
    }
    class CoursesViewHolder(val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root)
}