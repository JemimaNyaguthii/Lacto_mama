package com.ajolla.lactomama.mother

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.databinding.ActivityArticlesDetailsBinding
import com.ajolla.lactomama.ui.bookings.BookingsFragment

class ArticlesDetailsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityArticlesDetailsBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
      binding= ActivityArticlesDetailsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val articleId = intent.getStringExtra("ARTICLE_ID")
        val content = intent.getStringExtra("CONTENT")
        populateArticleDetails(
            articleId,content,
        )
        binding.ivBackCourse.setOnClickListener {
            onBackPressed()
            val intent = Intent(this,HomeFragment::class.java)
            startActivity(intent)
        }
    }
    private fun populateArticleDetails(
        articleId:String?,
        content: String?,
    ) {
        binding.tvId.text=articleId
        binding.tvContent.text=content
    }
}