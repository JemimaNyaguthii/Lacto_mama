package com.ajolla.lactomama.ui.bookings

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.ajolla.lactomama.databinding.FragmentBookingsBinding
import com.ajolla.lactomama.model.LactationistAdapter
import com.ajolla.lactomama.model.LactationistResponse
import com.ajolla.lactomama.viewModel.LactationistViewModel

class BookingsFragment : Fragment() {
    private lateinit var binding: FragmentBookingsBinding
    private val viewModel: LactationistViewModel by viewModels()
    private lateinit var lactationistAdapter: LactationistAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBookingsBinding.inflate(inflater, container, false)

        fetchLactationists()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
   lactationistAdapter = LactationistAdapter(emptyList())
//        val layoutmanager= LinearLayoutManager(requireContext())
        binding.rvLactationists.layoutManager = GridLayoutManager(requireContext(),2)
        binding.rvLactationists.adapter = lactationistAdapter
//        binding.rvLactationists.layoutManager = layoutmanager


    }


    fun fetchLactationists(){
        viewModel.fetchLactationists()
        viewModel.lactationistLiveData.observe(viewLifecycleOwner) { lactationists->
            val adapterHolder = LactationistAdapter(lactationists ?: emptyList())
            binding.rvLactationists.adapter = adapterHolder
//            Toast.makeText(
//                requireContext(),
//                "Fetched ${lactationists?.size} mothers",
//                Toast.LENGTH_LONG
//            ).show()
        }

        viewModel.errorLiveData.observe(viewLifecycleOwner) { error ->
            Toast.makeText(requireContext(), error, Toast.LENGTH_LONG).show()
        }

    }
}
