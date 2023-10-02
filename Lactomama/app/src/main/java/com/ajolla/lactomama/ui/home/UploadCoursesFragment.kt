package com.ajolla.lactomama.ui.home

    import android.annotation.SuppressLint
    import android.app.Activity
    import android.content.Intent
    import android.os.Bundle
    import android.view.LayoutInflater
    import android.view.View
    import android.view.ViewGroup
    import android.widget.Button
    import androidx.fragment.app.Fragment
    import androidx.recyclerview.widget.RecyclerView
    import com.ajolla.lactomama.R
    import com.ajolla.lactomama.ui.ArticleAdapter
    import com.ajolla.lactomama.ui.EducationalMaterialData

class UploadCoursesFragment : Fragment() {
        private val educationalMaterialList = mutableListOf<EducationalMaterialData>()
        private lateinit var adapter: ArticleAdapter
        override fun onCreateView(
            inflater: LayoutInflater, container: ViewGroup?,
            savedInstanceState: Bundle?
        ): View? {
            val view = inflater.inflate(R.layout.fragment_upload_courses, container, false)
            val uploadButton = view.findViewById<Button>(R.id.btnupload22)
            uploadButton.setOnClickListener {
                val intent = Intent(Intent.ACTION_GET_CONTENT)
                intent.type = "application/pdf, image/*, video/*"
                startActivityForResult(intent, 1)
            }

            val recyclerView = view.findViewById<RecyclerView>(R.id.rvcourse)
            adapter = ArticleAdapter(educationalMaterialList)
            recyclerView.adapter = adapter
            return view
        }    private fun updateRecyclerView(educationalMaterials: List<EducationalMaterialData>) {
            educationalMaterialList.clear()
            educationalMaterialList.addAll(educationalMaterials)
            adapter.notifyDataSetChanged()
        }
    }