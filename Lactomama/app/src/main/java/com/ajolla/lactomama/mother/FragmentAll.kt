package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.databinding.FragmentAllBinding
import CoursesRvAdapter
import com.ajolla.lactomama.model.CourseDetailsActivity
import java.io.Serializable

class FragmentAll : Fragment() {
    private var _binding: FragmentAllBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAllBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val course = CoursesData(
            "ivCareerWoman",
            "Dr. Roweena Njeri",
            "Get in Shape: Tips to Stay Healthy While Lactating",
            "2000",
            ".7 min read. 12 Oct"
        )

        val courseList = listOf(course)
        val courseAdapter = CoursesRvAdapter(courseList) { course ->
            addCourseToCart(course)
        }

        binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
        binding.rvCourses.adapter = courseAdapter
    }

    private fun addCourseToCart(course: CoursesData) {
        val detailsIntent = Intent(requireContext(), CourseDetailsActivity::class.java)
        detailsIntent.putExtra("course", course as Serializable)
        startActivity(detailsIntent)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}