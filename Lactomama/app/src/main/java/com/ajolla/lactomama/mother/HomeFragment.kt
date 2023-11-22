package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentHomeBinding
import com.ajolla.lactomama.mother.cart.ShoppingCartActivity
import com.ajolla.lactomama.ui.ArticleAdapter
import com.ajolla.lactomama.ui.HomeFragment
import com.ajolla.lactomama.ui.MotherProfile
import com.ajolla.lactomama.ui.home.ArticleData
import com.ajolla.lactomama.viewModel.EducationalMaterialsViewModel

class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    val eduViewModel: EducationalMaterialsViewModel by viewModels()
    private val binding get() = _binding!!
    private lateinit var educationalAdapter: ArticleAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.ivFrequency.setOnClickListener {
            val navController = findNavController()
            navController.navigate(R.id.home_to_bookings)
        }

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

