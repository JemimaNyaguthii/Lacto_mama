package com.ajolla.lactomama.ui.home

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.AppointmentlayoutBinding
import com.ajolla.lactomama.model.appointmentdata

class AppointmentsAdapter(var appointmentlist:List<appointmentdata>):RecyclerView.Adapter<AppointmentsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AppointmentsViewHolder {
        val binding=AppointmentlayoutBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return AppointmentsViewHolder(binding)
    }

    override fun getItemCount(): Int {
        return appointmentlist.size
    }

    override fun onBindViewHolder(holder: AppointmentsViewHolder, position: Int) {
        val currentAppointment = appointmentlist.get(position)
        val binding = holder.binding
        binding.textView20.text=currentAppointment.available
        binding.textView21.text=currentAppointment.firstName
        binding.textView22.text=currentAppointment.need

    }


}
class AppointmentsViewHolder(var binding:AppointmentlayoutBinding) : RecyclerView.ViewHolder(binding.root)









