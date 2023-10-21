package com.ajolla.lactomama.mother

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.ChatFragment
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityHomePageBinding
import com.ajolla.lactomama.ui.bookings.BookingsFragment

class HomePageActivity : AppCompatActivity() {
lateinit var binding: ActivityHomePageBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityHomePageBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    override fun onResume() {
        super.onResume()
        setupBottomNav()

    }
    fun setupBottomNav() {
        binding.bnvHome.setOnItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, HomeFragment()).commit()
                    true
                }

                R.id.booking -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, BookingsFragment()).commit()
                    true


                }

//                R.id.chat -> {
//                    supportFragmentManager
//                        .beginTransaction()
//                        .replace(R.id.fcvHome, ChatFragment()).commit()
//                    true
//                }

                R.id.course -> {
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, CoursesFragment()).commit()
                    true
                }

                else -> false
            }
        }
    }

}