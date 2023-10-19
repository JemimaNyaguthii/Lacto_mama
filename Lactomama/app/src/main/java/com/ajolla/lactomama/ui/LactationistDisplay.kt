//package com.ajolla.lactomama.ui
//
//import androidx.appcompat.app.AppCompatActivity
//import android.os.Bundle
//import android.widget.Toast
//import androidx.activity.viewModels
//import androidx.lifecycle.Observer
//import androidx.recyclerview.widget.LinearLayoutManager
//import com.ajolla.lactomama.R
//import com.ajolla.lactomama.databinding.ActivityLactationistDisplayBinding
//import com.ajolla.lactomama.model.LactationistAdapter
//import com.ajolla.lactomama.viewModel.LactationistViewModel
//
//class LactationistDisplay : AppCompatActivity() {
//    private val lactationistViewModel:LactationistViewModel by viewModels()
//    lateinit var binding: ActivityLactationistDisplayBinding
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        binding = ActivityLactationistDisplayBinding.inflate(layoutInflater)
//        setContentView(binding.root)
//    }
//
//    override fun onResume() {
//        super.onResume()
////        lactationistViewModel.fetchLactationists()
////        lactationistViewModel.lactationistLiveData.observe(this, Observer { lactationistList->
////            var lactationistAdapter = LactationistAdapter(lactationistList ?: emptyList())
////            binding.rvLactationistDisplay.layoutManager = LinearLayoutManager(this@LactationistDisplay)
//            binding.rvLactationistDisplay.adapter = lactationistAdapter
//            Toast.makeText(baseContext,"fetched ${lactationistList?.size} lactationist",Toast.LENGTH_LONG).show()
//        })
//        lactationistViewModel.errorLiveData.observe(this, Observer { error->
//            Toast.makeText(baseContext,error,Toast.LENGTH_LONG).show()
//        })
//    }
//
//}