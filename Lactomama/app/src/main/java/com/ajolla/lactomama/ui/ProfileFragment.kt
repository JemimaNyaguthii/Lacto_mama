package com.ajolla.lactomama.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import com.ajolla.lactomama.R
import com.ajolla.lactomama.api.ApiClient
import com.ajolla.lactomama.api.ApiInterface
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)
      val logoutButton:Button = view.findViewById(R.id.btnlogout)
        val apiService = ApiClient.buildClient((ApiInterface::class.java))

        logoutButton.setOnClickListener {
            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = apiService.logout()
                    if (response.isSuccessful) {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Logout Successful", Toast.LENGTH_SHORT).show()
                            val intent = Intent(requireContext(), LactationistLogin::class.java)
                            startActivity(intent)
                            activity?.finish()
                        }
                    } else {
                        withContext(Dispatchers.Main) {
                            Toast.makeText(requireContext(), "Logout Failed", Toast.LENGTH_SHORT).show()
                        }
                    }
                } catch (e: Exception) {
                    withContext(Dispatchers.Main) {
                        Toast.makeText(requireContext(), "Network Error", Toast.LENGTH_SHORT).show()
                    }
                }
            }
        }


        val editProfileButton: Button = view.findViewById(R.id.btnedit)
        val allBookingsButton: Button = view.findViewById(R.id.btnbook)
        val allUploadedCoursesButton: Button = view.findViewById(R.id.btncourses)

        editProfileButton.setOnClickListener {
            val intent = Intent(requireContext(), EditProfile::class.java)
            startActivity(intent)

        }

        allBookingsButton.setOnClickListener {
            val intent = Intent(requireContext(), Appointments::class.java)
            startActivity(intent)
        }

        allUploadedCoursesButton.setOnClickListener {
            val intent = Intent(requireContext(), Listcourse::class.java)
            startActivity(intent)
        }

        return view
    }


}
