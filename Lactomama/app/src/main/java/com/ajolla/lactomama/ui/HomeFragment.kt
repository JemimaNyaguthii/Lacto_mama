package com.ajolla.lactomama.ui

import android.content.Intent
import android.os.Bundle
import android.util.Log
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
import com.ajolla.lactomama.mother.cart.ShoppingCartActivity
import com.ajolla.lactomama.ui.home.ArticleData
import com.ajolla.lactomama.viewModel.EducationalMaterialsViewModel

class HomeFragment : Fragment() {
    val eduViewModel: EducationalMaterialsViewModel by viewModels()
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

        educationalAdapter = ArticleAdapter(emptyList(), requireContext())
        binding.rvArticles.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvArticles.adapter = educationalAdapter // Set the adapter

        val animator = DefaultItemAnimator()
        animator.addDuration = 1000
        binding.rvArticles.itemAnimator = animator

        eduViewModel.eduLiveData.observe(viewLifecycleOwner, Observer { articlesList ->
            if (articlesList != null) {
                Log.d("ArticleCount", "Fetched ${articlesList.size} articles") // Log the size
                educationalAdapter.updateArticles(articlesList)
            }
        })
        eduViewModel.errorLiveData.observe(viewLifecycleOwner, Observer { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        })
        eduViewModel.fetchArticles()
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}




