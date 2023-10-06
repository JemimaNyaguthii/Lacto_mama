package com.ajolla.lactomama.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.EducationalListItemBinding

class ArticleAdapter(var articles:MutableList<EducationalMaterialData>) : RecyclerView.Adapter<ArticleAdapter.EducationalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationalViewHolder {
        val binding =
            EducationalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationalViewHolder(binding)
    }

    override fun onBindViewHolder(holder: EducationalViewHolder, position: Int) {
        val currentArticle = articles[position]
        val binding = holder.binding
        binding.tvTitle.text = currentArticle.title
        binding.tvId.text = currentArticle.toString()
        binding.tvContent.text = currentArticle.content
        binding.tvCreated.text = currentArticle.createdAt
        binding.tvTitlee.text = currentArticle.description
        binding.tvLactationist.text = currentArticle.lactationist.toString()
        binding.tvUpdated.text = currentArticle.updatedAt
        binding.ivImage.tag= currentArticle.image
    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class EducationalViewHolder(var binding: EducationalListItemBinding) :
        RecyclerView.ViewHolder(binding.root)
}