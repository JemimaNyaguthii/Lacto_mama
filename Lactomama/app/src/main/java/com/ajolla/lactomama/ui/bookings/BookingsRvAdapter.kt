package com.ajolla.lactomama.ui.bookings

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.LactationistListItemsBinding
import com.ajolla.lactomama.lactationist.LactationistDetailsActivity
import com.squareup.picasso.Picasso

class BookingsRvAdapter (var bookingList:List<BookingsData>): RecyclerView.Adapter<BookingsRvAdapter.BookingsViewHolder>()  {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookingsViewHolder {
        val binding =LactationistListItemsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return BookingsViewHolder(binding)
    }

    override fun onBindViewHolder(holder: BookingsViewHolder, position: Int) {
        var currentBooking = bookingList.get(position)
        var binding = holder.binding
        binding.ivLactationist.tag= currentBooking.image
        binding.tvLactationistName.text = currentBooking.title
        Picasso
            .get().load(currentBooking.image)
            .resize(700, 700)
            .centerInside()
            .into(binding.ivLactationist)
        binding.tvLactationistName.setOnClickListener {
            val intent = Intent(holder.itemView.context, LactationistDetailsActivity::class.java)
            holder.itemView.context.startActivity(intent)
        }

    }
    override fun getItemCount(): Int {
        return bookingList.size
    }
    class BookingsViewHolder(var binding:LactationistListItemsBinding): RecyclerView.ViewHolder(binding.root)
}


