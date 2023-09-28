package com.ajolla.lactomama.ui

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import android.widget.VideoView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.ajolla.lactomama.R

class CoursesFragment : Fragment() {

    private val fileList = mutableListOf<String>()
    private lateinit var adapter: CourseAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_courses, container, false)

        val uploadButton = view.findViewById<Button>(R.id.btnupload22)
        uploadButton.setOnClickListener {
            val intent = Intent(Intent.ACTION_GET_CONTENT)
            intent.type = "application/pdf, image/*, video/*"
            startActivityForResult(intent, 1)
        }


        val recyclerView = view.findViewById<RecyclerView>(R.id.rvcourse)
        adapter = CourseAdapter(fileList)
        recyclerView.adapter = adapter
// Show an error message or prevent further action
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == 1 && resultCode == Activity.RESULT_OK) {
            val fileUri = data?.data

            if (fileUri != null) {
                val fileName = fileUri.lastPathSegment ?: ""
                val fileExtension = fileName.substringAfterLast('.', "")

                if (fileExtension.isNotEmpty()) {
                    when {
                        fileExtension.equals("pdf", ignoreCase = true) || fileExtension in arrayOf("jpg", "jpeg", "png") -> {
                            fileList.add(fileName)
                            adapter.notifyItemInserted(fileList.size - 1)
                            Toast.makeText(requireContext(), "File uploaded successfully", Toast.LENGTH_SHORT).show()
                        }
                        fileExtension.equals("mp4", ignoreCase = true) -> {

                            Toast.makeText(requireContext(), "Video uploaded successfully", Toast.LENGTH_SHORT).show()
                        }
                        else -> {

                            Toast.makeText(requireContext(), "Unsupported file type", Toast.LENGTH_SHORT).show()
                        }
                    }
                } else {

                    Toast.makeText(requireContext(), "Unsupported file type", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }
}
