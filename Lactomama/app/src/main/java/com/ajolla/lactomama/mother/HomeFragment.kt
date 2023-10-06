package com.ajolla.lactomama.mother

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ajolla.lactomama.databinding.FragmentHomeBinding
import com.ajolla.lactomama.ui.EducationalRvAdapter
import com.ajolla.lactomama.ui.MotherProfile
import com.ajolla.lactomama.ui.home.ArticleData

class
HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        displayArticles()
        binding.ivMotherProfile.setOnClickListener {
            val intent = Intent(requireContext(), MotherProfile::class.java)
            startActivity(intent)
        }
    }

    fun displayArticles(){
        var article1= ArticleData("https://img.freepik.com/free-psd/flat-design-baby-template_23-2150070658.jpg?w=1380&t=st=1695567351~exp=1695567951~hmac=cd4d5ee9fc710cdbc9e7d1b6e86fd2ccaa3d337f3f157b6c5ef70e9a836860da","Your newborns first week")
        var article2= ArticleData("https://i0.wp.com/handtohold.org/wp-content/uploads/2020/08/Black-Breastfeeding-Week-2020.png?resize=1024%2C600&ssl=1","Breastfeeding your baby")
        var article3= ArticleData("https://s.yimg.com/ny/api/res/1.2/BXyhk7nfFUy4Qtl1NfBtvg--/YXBwaWQ9aGlnaGxhbmRlcjt3PTI0MDA7aD0xMzUwO2NmPXdlYnA-/https://s.yimg.com/os/creatr-uploaded-images/2022-08/cc4c69c0-23eb-11ed-bbf8-85e06d6a808a","Controversies in Breastfeeding")
        var articles= listOf(article1,article2,article3)
        var educationalAdapter= EducationalRvAdapter(articles)
        binding.rvArticles.layoutManager= GridLayoutManager(requireContext(),2)
        binding.rvArticles.adapter=educationalAdapter
    }
}
