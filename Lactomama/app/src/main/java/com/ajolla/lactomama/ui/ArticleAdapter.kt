package com.ajolla.lactomama.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.EducationalListItemBinding
import com.ajolla.lactomama.ui.home.ArticleData
import com.squareup.picasso.Picasso

class ArticleAdapter(private var articles: List<ArticleData>) : RecyclerView.Adapter<ArticleAdapter.ArticleViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ArticleViewHolder {
        val binding = EducationalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ArticleViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ArticleViewHolder, position: Int) {
        val currentArticle = articles[position]
        val binding = holder.binding

        // Set the data to the views
        binding.tvTitle.text = currentArticle.title

        // Load the image only if it's not null
        currentArticle.image?.let { imageUrl ->
            Picasso.get()
                .load(imageUrl)
                .resize(80, 80)
                .centerInside()
                .into(binding.ivImage)
        }
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    // Update the articles in the adapter
    fun updateArticles(newArticles: List<ArticleData>) {
        articles = newArticles
        notifyDataSetChanged()
    }

    class ArticleViewHolder(var binding: EducationalListItemBinding) : RecyclerView.ViewHolder(binding.root)
}
