package com.ajolla.lactomama.ui


import android.view.LayoutInflater
import android.view.ViewGroup
import com.squareup.picasso.Picasso
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.EducationalListItemBinding

class ArticleAdapter (var articles:List<EducationalMaterialData>): RecyclerView.Adapter<ArticleAdapter.EducationalViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EducationalViewHolder{
        val binding =EducationalListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return EducationalViewHolder(binding)
    }
    override fun onBindViewHolder(holder: EducationalViewHolder, position: Int) {
        val currentArticles =articles.get(position)
        val binding = holder.binding
        binding.tvTitle.text = currentArticles.title
        binding.ivCoverOfArticle.tag=currentArticles.image
        Picasso
            .get().load(currentArticles.image)
            .resize(80, 80)
            .centerInside()
            .into(binding.ivCoverOfArticle)

    }

    override fun getItemCount(): Int {
        return articles.size
    }

    class EducationalViewHolder(var binding: EducationalListItemBinding) :
        RecyclerView.ViewHolder(binding.root)

}