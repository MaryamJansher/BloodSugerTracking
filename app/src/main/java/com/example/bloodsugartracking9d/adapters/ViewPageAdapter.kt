package com.example.bloodsugartracking9d.adapters

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.bloodsugartracking9d.fragment.TipsFragment1
import com.example.bloodsugartracking9d.fragment.TipsFragment2

class ViewPageAdapter (fm: FragmentManager) : FragmentPagerAdapter(fm) {
    override fun getCount(): Int {
        return 3;
    }

    override fun getItem(position: Int): Fragment {
        when(position) {
            0 -> {
                return TipsFragment1()
            }
            1 -> {
                return TipsFragment2()
            }
            else -> {

                return TipsFragment1()
            }
        }
    }

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> {
                return "Tab 1"
            }
            1 -> {
                return "Tab 2"
            }

        }
        return super.getPageTitle(position)
    }

}