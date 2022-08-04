package com.example.bloodsugartracking9d.fragment

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.activities.MainActivity
import com.example.bloodsugartracking9d.databinding.FragmentStatisticsBinding
import com.example.bloodsugartracking9d.koincomponents.ViewmodelKoin
import com.example.bloodsugartracking9d.room.UserDetail
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import java.text.DecimalFormat
import java.text.ParseException
import java.util.*
import kotlin.collections.ArrayList
import kotlin.coroutines.CoroutineContext

class StatisticsFragment : Fragment(), View.OnClickListener, CoroutineScope {

    private val mViewModel: ViewmodelKoin by viewModel()
    lateinit var statisticsBinding: FragmentStatisticsBinding
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    var getlistbasedonmeasuremements = ArrayList<UserDetail>()


    override fun onAttach(context: Context) {
        super.onAttach(context)
       // (activity as MainActivity).drawerIcon()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {

        statisticsBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_statistics, container, false)

        return statisticsBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        statisticsBinding.vMeasurementtime.setOnClickListener(this)


        getlistbasedonmeasurements(statisticsBinding.etMeasurementtime.text as String)


    }

    override fun onClick(view: View?) {

        when (view?.id) {

            R.id.v_measurementtime -> {

                mViewModel.showMeasurementDialog(requireActivity())

            }
        }
    }

    fun getlistbasedonmeasurements(measurement: String) {

        mViewModel.getuserdatalistwithmeasurements(measurement).observe(requireActivity()) {

            getlistbasedonmeasuremements = it as ArrayList<UserDetail>

        }
    }


    override fun onResume() {
        super.onResume()

        mViewModel.getselectedmeasurementlivedata.observe(requireActivity()) {
            statisticsBinding.etMeasurementtime.text = it

            getlistbasedonmeasurements(it)

        }

    }


}


