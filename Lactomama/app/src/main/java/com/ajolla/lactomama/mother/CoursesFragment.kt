package com.ajolla.lactomama.mother

import android.os.Bundle
import android.view.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import androidx.viewpager.widget.ViewPager
import com.ajolla.lactomama.R
import com.ajolla.lactomama.databinding.FragmentCoursesBinding
import com.google.android.material.tabs.TabLayout

class CoursesFragment : Fragment() {

    private var _binding: FragmentCoursesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        val view = inflater.inflate(R.layout.fragment_courses, container, false)
        val viewPager = view.findViewById<ViewPager>(R.id.viewPager)
        val tabLayout = view.findViewById<TabLayout>(R.id.tabLayout)
        val adapter = MyPagerAdapter(childFragmentManager)

        viewPager.adapter = adapter
        tabLayout.setupWithViewPager(viewPager)
        setHasOptionsMenu(true)
        return view
    }
    override fun onResume() {
        super.onResume()
    }

    private class MyPagerAdapter(fm: FragmentManager) :
        FragmentPagerAdapter(fm, BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT) {
        override fun getCount(): Int {
            return 3
        }

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return FragmentAll()
                1 -> return progressFragment()
                2 -> return completedFragment()
                else -> throw IllegalArgumentException("Invalid position")
            }
        }

        override fun getPageTitle(position: Int): CharSequence? {
            return when (position) {
                0 -> "All"
                1 -> "In Progress"
                2 -> "Completed"
                else -> null
            }
        }
    }
}
