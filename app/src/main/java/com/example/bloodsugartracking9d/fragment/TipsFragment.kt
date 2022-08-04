package com.example.bloodsugartracking9d.fragment

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.viewpager.widget.ViewPager
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.activities.MainActivity
import com.example.bloodsugartracking9d.adapters.ViewPageAdapter
import com.example.bloodsugartracking9d.databinding.FragmentTipsBinding

class TipsFragment : Fragment() {

    lateinit var binding: FragmentTipsBinding

    override fun onAttach(context: Context) {
        super.onAttach(context)
        //(activity as MainActivity).drawerIcon()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_tips, container, false)
        val viewPager = binding.viewPager
        val tablayout = binding.tabLayout
        viewPager.adapter = ViewPageAdapter(requireActivity().supportFragmentManager)
        tablayout.setupWithViewPager(viewPager)

        return binding.root


    }


}