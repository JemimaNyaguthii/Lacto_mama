package com.ajolla.lactomama.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentHome2Binding


class HomeFragment : Fragment() {
private var _binding:FragmentHome2Binding?=null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHome2Binding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        displayArticles()
    }
    fun displayArticles(){
        var article1=EducationalMaterialData("https://media.istockphoto.com/id/1044543794/photo/beautiful-african-american-mother-holds-newborn-baby-in-the-living-room.jpg?s=612x612&w=0&k=20&c=4ufPnG8hm_pH28iS6knqxvy7gbeLfT-QEHL45f4nNX0= ","Tactics")
        var article2=EducationalMaterialData("https://media.istockphoto.com/id/1153667706/photo/loving-mother-holding-newborn-baby-at-home-in-loft-apartment.jpg?s=612x612&w=0&k=20&c=GEX-bljGGzqT0DDkU_UYNppWllLvyZ5axqk-u4PhhS8=","breastfeeding")
        var article3=EducationalMaterialData("https://www.verywellfamily.com/thmb/u3UTmnxZCsZzEIgBNhQDh7c5HzE=/1500x0/filters:no_upscale():max_bytes(150000):strip_icc()/black-mother-cuddling-sleeping-baby-son-on-sofa-758282421-5b1a841f8e1b6e0036b6face.jpg","pattern")
        var article4=EducationalMaterialData("https://www.thebermudian.com/wp-content/uploads/2022/10/mother-and-baby-2.jpg"," experience")
        var article5=EducationalMaterialData("https://us.123rf.com/450wm/anyka/anyka1801/anyka180100023/94097942-loving-african-mother-holding-her-11-days-old-newborn-baby.jpg?ver=6","feed culture")
        var article6 =EducationalMaterialData("https://mumsvillage.com/wp-content/uploads/2015/11/new-african-mother-with-baby.jpg","happy baby")
        var articles= listOf(article1,article2,article3,article4,article5,article6)
        var educationalAdapter=ArticleAdapter(articles)
        binding.rvArticles.layoutManager= GridLayoutManager(requireContext(),2)
        binding.rvArticles.adapter=educationalAdapter
    }

}