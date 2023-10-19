package com.ajolla.lactomama.model

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.LactationistListItemsBinding

//class LactationistAdapter(private var lactationistList: List<Lactationist>): RecyclerView.Adapter<LactationistAdapter.LactationistViewHolder>() {
//    fun updateData(newData: List<Lactationist>) {
//        lactationistList = newData
//        notifyDataSetChanged()}
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LactationistViewHolder {
//        val binding =
//            LactationistListItemsBinding.inflate(LayoutInflater.from(parent.context),parent,false)
//        return LactationistViewHolder(binding)
//    }
//
//    override fun onBindViewHolder(holder: LactationistAdapter.LactationistViewHolder, position: Int) {
//        var currentLactationist = lactationistList[position]
//        holder.bind(currentLactationist)
//
//
//    }
//
//    override fun getItemCount(): Int {
//        return lactationistList.size
//    }
//
//    inner class LactationistViewHolder(private val binding: LactationistListItemsBinding):
//    RecyclerView.ViewHolder(binding.root){
//        fun bind(currentLactationist: Lactationist){
//            binding.apply {
//                 tvFirstName.text = currentLactationist.firstName
//                 tvEmail.text= currentLactationist.email
//                 tvBio.text = currentLactationist.bio
//            }
//        }
//
//    }
//
//}

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
       }
   }
}
class LactationistViewHolder( val binding: LactationistListItemsBinding):RecyclerView.ViewHolder(binding.root)
