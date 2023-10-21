package com.ajolla.lactomama.model

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.mother.cart.Course
import com.ajolla.lactomama.databinding.CourseItemBinding


class CoursesRvAdapter(
    private val context: Context,
    private val courses: List<Course>,
    private val addToCartFunction: (Course) -> Unit
) : RecyclerView.Adapter<CoursesRvAdapter.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = CourseItemBinding.inflate(LayoutInflater.from(context), parent, false)
        return ViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return courses.size
    }
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        viewHolder.bindCourse(courses[position])
    }

    inner class ViewHolder(private val binding: CourseItemBinding) : RecyclerView.ViewHolder(binding.root) {
        private val titleTextView: TextView = binding.courseTitle
        private val descriptionTextView: TextView = binding.courseDescription
        private val priceTextView: TextView = binding.coursePrice
        private val addToCartButton: ImageButton = binding.addToCart

        init {
            itemView.setOnClickListener {
                val selectedCourse = courses[adapterPosition]
                addToCartFunction(selectedCourse)
            }
            addToCartButton.setOnClickListener {
                val selectedCourse = courses[adapterPosition]
                addToCartFunction(selectedCourse)
            }
        }

        fun bindCourse(course: Course) {
            titleTextView.text = course.title
            descriptionTextView.text = course.description
            priceTextView.text = "$${course.price}"
        }
    }
}