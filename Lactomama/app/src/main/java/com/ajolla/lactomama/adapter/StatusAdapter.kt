package com.ajolla.lactomama.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.RowBinding
import com.ajolla.lactomama.model.StatusDataClass

class StatusAdapter(

    var data: MutableList<StatusDataClass>,
) : RecyclerView.Adapter<StatusAdapter.ViewHolder>() {

    class ViewHolder(val binding: RowBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = RowBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        if(data[position].feedingNote != null)
        {
            holder.binding.statusInfo.text = "Feeding"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.icon_wet_selected)
        }
        if(data[position].diaperNote != null)
        {
            holder.binding.statusInfo.text = "Diaper"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.diaper_select)

        }
        if(data[position].sleepNote != null)
        {
            holder.binding.statusInfo.text = "Sleep"
            holder.binding.statusToday.text = data[position].today
            holder.binding.statusTime.text = data[position].hour
            holder.binding.statusImageView.setImageResource(R.drawable.sleeping_selected)
        }
    }
    override fun getItemCount(): Int {
        return data.size
    }
}