package com.ajolla.lactomama.ui
import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import com.ajolla.lactomama.Appointments
import com.ajolla.lactomama.R

class ProfileFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_profile, container, false)

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
