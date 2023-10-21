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
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import com.ajolla.lactomama.databinding.FragmentHomeBinding
import com.ajolla.lactomama.ui.ArticleAdapter
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
        educationalAdapter = ArticleAdapter(mutableListOf())
        binding.rvArticles.layoutManager = GridLayoutManager(requireContext(), 2)
        binding.rvArticles.adapter = educationalAdapter
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


    override fun onResume() {
        super.onResume()

        binding.ivMotherProfile.setOnClickListener {
            val intent = Intent(requireContext(), MotherProfile::class.java)
            startActivity(intent)
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
