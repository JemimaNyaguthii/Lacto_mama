package com.ajolla.lactomama

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

class onBoardingAdapter (activity:FragmentActivity,private  val pagerList: ArrayList<Page>):
    FragmentStateAdapter(activity){
    override fun getItemCount(): Int {
        return pagerList.size
    }

    override fun createFragment(position: Int): Fragment {
        return onBoardingfragment(pagerList[position])
    }

}