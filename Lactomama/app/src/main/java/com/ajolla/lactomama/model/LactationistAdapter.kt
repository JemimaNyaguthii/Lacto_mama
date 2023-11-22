package com.ajolla.lactomama.model

import android.annotation.SuppressLint
import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.LactationistListItemsBinding
import com.ajolla.lactomama.lactationist.LactationistDetailsActivity


class LactationistAdapter(var lactationistList: List<Lactationist>): RecyclerView.Adapter<LactationistViewHolder>() {
   override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LactationistViewHolder {
     val binding = LactationistListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
      return LactationistViewHolder(binding)
   }
    override fun getItemCount(): Int {
        return lactationistList.size
    }
 override fun onBindViewHolder(holder: LactationistViewHolder, position: Int) {
    val currentLactationist = lactationistList[position]

    holder.binding.apply {
        tvFirstName.text = currentLactationist.firstName
        tvEmail.text = currentLactationist.email
        tvBio.text = currentLactationist.bio

        // Set an OnClickListener for the root view of the item
        root.setOnClickListener {
            val intent = Intent(holder.itemView.context, LactationistDetailsActivity::class.java)
            intent.putExtra("FIRST_NAME", currentLactationist.firstName)
            intent.putExtra("BIO", currentLactationist.bio)

            holder.itemView.context.startActivity(intent)
        }
    }
}
}
class LactationistViewHolder( val binding: LactationistListItemsBinding):RecyclerView.ViewHolder(binding.root)
