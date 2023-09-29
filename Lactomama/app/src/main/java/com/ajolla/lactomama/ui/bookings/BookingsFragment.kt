package com.ajolla.lactomama.ui.bookings

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.PixelCopy
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ajolla.lactomama.databinding.FragmentBookingsBinding
import com.ajolla.lactomama.databinding.LactationistListItemsBinding
import com.squareup.picasso.Picasso


class BookingsFragment : Fragment() {
    private var _binding: FragmentBookingsBinding?=null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?

    ): View? {
        _binding = FragmentBookingsBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun onResume() {
        super.onResume()
        displayLactationist()
    }


    fun displayLactationist(){
        val lactationist1= BookingsData("https://i.pinimg.com/564x/9c/ca/95/9cca95887b43923ffff65a197b58cf45.jpg","Dr.Rowena")
        val lactationist2= BookingsData("https://img.freepik.com/free-photo/front-view-smiley-man-wearing-lab-coat_23-2149633830.jpg?w=740&t=st=1695725575~exp=1695726175~hmac=30aa2a33278a2f5f91bbf7c425276da1fccef1b3dbea15d9bbb4917efcbe153a","Dr.Taj")
        val lactationist3= BookingsData("https://img.freepik.com/free-photo/close-up-health-worker_23-2149112513.jpg?w=740&t=st=1695725504~exp=1695726104~hmac=ff0db0189a3e03765c00a4ab9eaa2a10ebd8b02384a9991070d7d8ba8c68ea61","Dr.Jim")
        val lactationist4= BookingsData("https://img.freepik.com/free-photo/front-view-female-doctor-wearing-stethoscope_23-2149856262.jpg?w=740&t=st=1695566654~exp=1695567254~hmac=75ea783650f1c7e2480e146c09a6b1797eb1ab1ff0c720faad4f60126f5ea6eb","Dr.Sean")
        val lactationist6= BookingsData("https://i.pinimg.com/564x/9c/ca/95/9cca95887b43923ffff65a197b58cf45.jpg ","Dr.Becky")
        val lactationist7= BookingsData("https://i.pinimg.com/564x/9c/ca/95/9cca95887b43923ffff65a197b58cf45.jpg","Dr.Val")
        val lactationist8= BookingsData("https://i.pinimg.com/564x/9c/ca/95/9cca95887b43923ffff65a197b58cf45.jpg ","Dr.Joy")

        val lactationist= listOf(lactationist1,lactationist2,lactationist3,lactationist4,lactationist6,lactationist7,lactationist8)
        val lactationistAdapter= BookingsRvAdapter(lactationist)
        binding.rvLactationists.layoutManager= GridLayoutManager(requireContext(),2)
        binding.rvLactationists.adapter=lactationistAdapter
    }

}
