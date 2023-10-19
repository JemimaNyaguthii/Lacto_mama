package com.ajolla.lactomama.ui

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModel
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentHome2Binding
import com.ajolla.lactomama.viewModel.EducationalMaterialsViewModel

class HomeFragment : Fragment() {
    val eduViewModel:EducationalMaterialsViewModel by viewModels()
    private var _binding: FragmentHome2Binding? = null
    private val binding get() = _binding!!
    private lateinit var educationalAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        educationalAdapter = ArticleAdapter(mutableListOf())
        binding.rvArticles.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvArticles.adapter = educationalAdapter
        val animator = DefaultItemAnimator()
        animator.addDuration = 1000
        binding.rvArticles.itemAnimator = animator
    }

    override fun onResume() {
        super.onResume()
        eduViewModel.fetchArticles()
        eduViewModel.eduLiveData.observe(this, Observer { articlesList ->
            Toast.makeText(
                requireContext(),
                "fetched ${articlesList?.size} articles",
                Toast.LENGTH_LONG
            ).show()
            educationalAdapter.articles.clear()
            educationalAdapter.articles.addAll(articlesList)
            educationalAdapter.notifyDataSetChanged()
        })
        eduViewModel.errorLiveData.observe(this, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}





