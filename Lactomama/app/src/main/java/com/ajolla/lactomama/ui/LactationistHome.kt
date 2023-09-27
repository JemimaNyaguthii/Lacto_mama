package com.ajolla.lactomama.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityLactationistHomeBinding

class LactationistHome : AppCompatActivity() {
    lateinit var binding:ActivityLactationistHomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding= ActivityLactationistHomeBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }


    override fun onResume() {
        super.onResume()
        setupBottomNav()
    }
    fun setupBottomNav(){
        binding.bnvHome.setOnItemSelectedListener { menuItem->
            when(menuItem.itemId){
                R.id.home->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome,HomeFragment()).commit()
                    true
                }
                R.id.materials->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, MaterialsFragment()).commit()
                    true


                }
                R.id.profile->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome,ProfileFragment()).commit()
                    true
                }
                R.id.courses->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome, CoursesFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}