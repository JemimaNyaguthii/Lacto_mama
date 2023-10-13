package com.ajolla.lactomama.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.ActivityLactationistHomeBinding
import com.ajolla.lactomama.ui.HomeFragment
import com.ajolla.lactomama.ui.MaterialsFragment
import com.ajolla.lactomama.ui.ProfileFragment
import com.ajolla.lactomama.ui.home.UploadCoursesFragment

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
                        .replace(R.id.fcvHome, HomeFragment()).commit()
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
                        .replace(R.id.fcvHome, ProfileFragment()).commit()
                    true
                }
                R.id.courses_upload->{
                    supportFragmentManager
                        .beginTransaction()
                        .replace(R.id.fcvHome,UploadCoursesFragment()).commit()
                    true
                }
                else->false
            }
        }
    }
}