package com.example.bloodsugartracking9d.fragment


import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.Navigation.findNavController
import com.example.bloodsugartracking9d.R

import com.example.bloodsugartracking9d.activities.MainActivity
import com.example.bloodsugartracking9d.databinding.IndexScreenBinding


class MainFragment : Fragment()  , View.OnClickListener{

    lateinit var indexScreenBinding: IndexScreenBinding
    override fun onAttach(context: Context) {
        super.onAttach(context)
      //  (activity as MainActivity).drawerIcon()

    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        indexScreenBinding = DataBindingUtil.inflate(inflater, com.example.bloodsugartracking9d.R.layout.index_screen, container, false)
       indexScreenBinding.indexTracking.setOnClickListener(this)
        indexScreenBinding.indexStatistics.setOnClickListener(this)
        indexScreenBinding.indexHistory.setOnClickListener(this)
        indexScreenBinding.indexTips.setOnClickListener(this)

        return  indexScreenBinding.root
    }

    override fun onClick(view: View?) {

        when(view?.id){

            R.id.index_tracking ->{

                Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_tracking_fragment);
            }


            R.id.index_statistics -> {

                Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_statistics_fragment);
            }


            R.id.index_history ->{

                Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_history_fragment);
            }

            R.id.index_tips ->{

                Navigation.findNavController(view).navigate(R.id.action_main_fragment_to_tipsFragment);
            }
        }
    }


    override fun onResume() {
        super.onResume()
       // (activity as MainActivity).drawerIcon()
    }

}