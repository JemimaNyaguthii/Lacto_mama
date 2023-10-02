package com.ajolla.lactomama.mother.cart

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.CartListItemBinding
import com.ajolla.lactomama.databinding.CoursesListItemBinding

class CartRvAdapter(private val courses: MutableList<Course>) : RecyclerView.Adapter<CartRvAdapter.CoursesViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CoursesViewHolder {
        val binding = CartListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CoursesViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CoursesViewHolder, position: Int) {
        val currentCourse = courses[position]
        val binding = holder.binding

        binding.tvCart.text = currentCourse.name
        binding.tvDelete.text = currentCourse.name
        binding.ivCartImage.tag = currentCourse.image
        binding.ivCartImage2.tag = currentCourse.image
        binding.tvCourseName.text = currentCourse.name
        binding.tvPrice.text = currentCourse.price.toString()
        binding.tvTotalAmount.text = currentCourse.price.toString()
        binding.tvTotal.text = currentCourse.name
        binding.tvRemove.text = currentCourse.name

        binding.tvRemove.setOnClickListener {
            removeCourse(position)
        }
    }

    override fun getItemCount(): Int {
        return courses.size
    }

    private fun removeCourse(position: Int) {
        courses.removeAt(position)
        notifyItemRemoved(position)
    }

    class CoursesViewHolder(val binding: CartListItemBinding) : RecyclerView.ViewHolder(binding.root)
}