package com.ajolla.lactomama.mother

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.databinding.FragmentAllBinding
import CoursesRvAdapter
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.ajolla.lactomama.model.CourseDetailsActivity
import com.ajolla.lactomama.viewModel.CoursesViewModel

class FragmentAll : Fragment() {
    val coursesViewModel: CoursesViewModel by viewModels()
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
        }
        coursesViewModel.fetchCourses()
        coursesViewModel.coursesLiveData.observe(viewLifecycleOwner, Observer { courseList ->
            if (courseList != null) {
                Toast.makeText(
                    requireContext(),
                    "Fetched ${courseList.size} courses",
                    Toast.LENGTH_LONG
                ).show()

                binding.rvCourses.layoutManager = LinearLayoutManager(requireContext())
                binding.rvCourses.adapter = courseAdapter
            }
        })

        coursesViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}