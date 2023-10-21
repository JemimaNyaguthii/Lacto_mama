package com.ajolla.lactomama.ui

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.viewpager2.widget.ViewPager2
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityMainBinding
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding


    private val pagerList = arrayListOf(
        Page("Hello !", R.drawable.mom1, "A Friend that you can rely on to give you \n" +
                "guidance on your lactation journey.", "#22BB9B"),
        Page("Benefits", R.drawable.mom2, "We are the friend that will enable you get reliable information from lactation experts.", "22BB9B"),
        Page("Personalized Support", R.drawable.mother3
            , "Your breastfeeding journey is unique, tailored advice to your specific needs, ensuring confidence and comfort.", "22BB9A")
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.GetstartedBtn.setOnClickListener {
            val intent= Intent(this, MotherSignUp::class.java)
            startActivity(intent)
        }
        binding.skip.setOnClickListener {
            val intent = Intent(this , MotherSignUp::class.java)
            startActivity(intent)
        }



        val onBoardingViewPager2 = binding.Onboarding1viewpager
        val tabLayout = binding.tabLayout


        val nextBtn = binding.nextBtn
        val prevBtn = binding.previousBtn
        val onBoardingAdapter = onBoardingAdapter(this, pagerList)
        onBoardingViewPager2.adapter = onBoardingAdapter

        onBoardingViewPager2.registerOnPageChangeCallback(onBoardingPageChangeCallback)
        onBoardingViewPager2.overScrollMode = ViewPager2.OVER_SCROLL_NEVER

        TabLayoutMediator(tabLayout, onBoardingViewPager2) { _, _ -> }.attach()
        nextBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem < onBoardingAdapter.itemCount - 1) {
                onBoardingViewPager2.currentItem += 1
            } else {
                homeScreenIntent()
            }
        }

        prevBtn.setOnClickListener {
            if (onBoardingViewPager2.currentItem > 0) {
                onBoardingViewPager2.currentItem -= 1
            }
        }
    }

    override fun onDestroy() {
        binding.Onboarding1viewpager.unregisterOnPageChangeCallback(onBoardingPageChangeCallback)
        super.onDestroy()
    }

    private fun homeScreenIntent() {
        val homeIntent = Intent(this, Onboarding1Activity::class.java)
        startActivity(homeIntent)
    }

    private val onBoardingPageChangeCallback = object : ViewPager2.OnPageChangeCallback() {
        override fun onPageSelected(position: Int) {
            super.onPageSelected(position)
            val nextBtn = binding.nextBtn
            val prevBtn = binding.previousBtn
            val startBtn=binding.GetstartedBtn
            val skipBtn=binding.skip
            when (position) {
                0 -> {
                    nextBtn.visibility = View.VISIBLE
                    prevBtn.visibility = View.GONE
                    startBtn.visibility= View.GONE
                    skipBtn.visibility= View.VISIBLE

                }
                1 -> {
                    nextBtn.visibility = View.VISIBLE
                    prevBtn.visibility = View.VISIBLE
                    startBtn.visibility= View.GONE
                    skipBtn.visibility= View.VISIBLE

                }
                else -> {
                    nextBtn.visibility = View.GONE
                    prevBtn.visibility = View.GONE
                    startBtn.visibility= View.VISIBLE
                    skipBtn.visibility= View.GONE
                }

            }
        }

    }


}

