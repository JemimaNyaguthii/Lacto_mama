package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.databinding.ActivityAppointmentsBinding
import com.ajolla.lactomama.ui.home.AppointmentsAdapter
import com.ajolla.lactomama.viewModel.AppointmentViewModel

class Appointments : AppCompatActivity() {
    lateinit var binding: ActivityAppointmentsBinding
    val appointmentViewModel:AppointmentViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityAppointmentsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.imvback1.setOnClickListener {
            val intent =Intent( this,ProfileFragment::class.java)
            startActivity(intent)
        }
    }
    override fun onResume() {
        super.onResume()
        appointmentViewModel.fetchAppointments()
        appointmentViewModel.appointmentLiveData.observe(this, Observer { appointmentlist ->
            Toast.makeText(baseContext,
                "fetched ${appointmentlist?.size}posts",
                Toast.LENGTH_LONG
            ).show()
            binding.rvappointments.layoutManager=LinearLayoutManager(this@Appointments)
            binding.rvappointments.adapter=AppointmentsAdapter(appointmentlist)
        })
        appointmentViewModel.errorLiveData.observe(this,Observer{error->
            Toast.makeText(baseContext,error, Toast.LENGTH_LONG).show()
        })
    }
}



