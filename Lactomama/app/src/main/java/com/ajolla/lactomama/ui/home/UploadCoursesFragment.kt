package com.ajolla.lactomama.ui.home

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import com.ajolla.lactomama.R
import com.ajolla.lactomama.model.ArticleRequest
import com.ajolla.lactomama.ui.ArticleAdapter
import com.ajolla.lactomama.viewModel.ArticlesViewModel
import kotlinx.coroutines.launch
import android.widget.Toast
import com.ajolla.lactomama.ui.Success
import com.ajolla.lactomama.ui.SuceessScreen

class UploadCoursesFragment : Fragment() {
    private val educationalMaterialList = mutableListOf<ArticleData>()
    private lateinit var adapter: ArticleAdapter
    private lateinit var viewModel: ArticlesViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_upload_courses, container, false)
        val uploadButton = view.findViewById<Button>(R.id.btnupload2)

        viewModel = ViewModelProvider(this).get(ArticlesViewModel::class.java)

        uploadButton.setOnClickListener {
            val materialPost = ArticleRequest(
                id = 0,
                title = "Breastfeeding tips",
                description = "All about breastfeeding",
                createdAt = "2023-10-03T21:16:14.332853Z",
                updatedAt = "2023-10-03T21:16:14.332871Z",
                content = "Breastfeeding",
                lactationist = "3"
            )

            lifecycleScope.launch {
                val response = viewModel.postArticles(materialPost)
                if (response) {
                    Toast.makeText(requireContext(), "Post successful", Toast.LENGTH_SHORT).show()

                    val intent = Intent(requireContext(), Success::class.java)
                    startActivity(intent)
                } else {
                    Toast.makeText(requireContext(), "Post failed", Toast.LENGTH_SHORT).show()
                }
            }
        }


        return view
    }
}
