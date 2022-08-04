package com.example.bloodsugartracking9d.fragment


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import androidx.navigation.Navigation
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.bloodsugartracking9d.R
import com.example.bloodsugartracking9d.ScreenUtils
import com.example.bloodsugartracking9d.activities.MainActivity
import com.example.bloodsugartracking9d.adapters.PickerAdapter

import com.example.bloodsugartracking9d.all_interface.RecyclerViewclicks
import com.example.bloodsugartracking9d.all_interface.UnitsRecyclerStopScrolling
import com.example.bloodsugartracking9d.databinding.TrackingfragmentBinding
import com.example.bloodsugartracking9d.koincomponents.ViewmodelKoin
import com.example.bloodsugartracking9d.room.UserDetail
import com.example.bloodsugartracking9d.unit_mmol
import com.sazib.mypicker.picker.PickerLayoutManager
import kotlinx.coroutines.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import kotlin.coroutines.CoroutineContext


class TrackingFragment : Fragment(), View.OnClickListener, CoroutineScope {

    lateinit var trackingfragmentBinding: TrackingfragmentBinding
    private val mViewModel: ViewmodelKoin by viewModel()
    val measurements = ArrayList<String>()

    // lateinit var measurements_adapter: UnitsRecyclerAdapter
    private lateinit var sliderAdapter: PickerAdapter
    var medicines: Boolean = false
    var unit_selected: String = unit_mmol
    var blood_sugar_Selected: Int = 0
    override val coroutineContext: CoroutineContext
        get() = Dispatchers.Main
    //lateinit var  handler : CoroutineExceptionHandler

    override fun onAttach(context: Context) {
        super.onAttach(context)
       // (activity as MainActivity).drawerIcon()
        (activity as MainActivity).get_toolbar()


    }
    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {

        trackingfragmentBinding = DataBindingUtil.inflate(inflater, R.layout.trackingfragment, container, false)
        trackingfragmentBinding.etDate.setOnClickListener(this)
        trackingfragmentBinding.etTime.setOnClickListener(this)
        trackingfragmentBinding.etMeasurement.setOnClickListener(this)
        trackingfragmentBinding.buttonSave.setOnClickListener(this)
       /* val padding: Int = ScreenUtils.getScreenWidth(requireContext()) / 2 - ScreenUtils.dpToPx(
            requireContext(),
            40
        )*/
       // trackingfragmentBinding.rvHorizontalPicker.setPadding(padding, 0, padding, 0)
        sliderAdapter = PickerAdapter()


        launch {
            val listofmmolunits = async(Dispatchers.IO) {
                populatemmolunits()
            }
            listofmmolunits.await()
            withContext(Dispatchers.Main) {
                setLayoutManager()
                setHorizontalAdapter(measurements)

            }


        }



        return trackingfragmentBinding.root
    }


    fun addRecyclerviewMeasurements() {

        mViewModel.get_unit_selected.observe(viewLifecycleOwner, androidx.lifecycle.Observer {
            unit_selected = it

        })


        if (unit_selected == unit_mmol) {
            measurements.clear()
            populatemmolunits()
            setLayoutManager()
            setHorizontalAdapter(measurements)

        } else {
            measurements.clear()
            populatemgunits()
            setLayoutManager()
            setHorizontalAdapter(measurements)
        }


    }


    override fun onClick(view: View?) {
        when (view?.id) {

            R.id.et_label -> {


            }

            R.id.button_save -> {

                saveData(view)


            }

            R.id.et_time -> {

                mViewModel.showTimePicker(requireContext())

            }

            R.id.et_date -> {

                mViewModel.showDatePicker(requireContext())

            }


            R.id.et_measurement -> {

                mViewModel.showMeasurementDialog(requireActivity())
            }


        }
    }

    fun saveData(view: View) {
        CoroutineScope(Dispatchers.Default).launch {

                (Dispatchers.IO) {

                    val gettime = trackingfragmentBinding.etTime.text.toString()
                    val getdate = trackingfragmentBinding.etDate.text.toString()
                    val getmeasurements = trackingfragmentBinding.etMeasurement.text.toString()
                    val getnotes = trackingfragmentBinding.etLabel.text.toString()
                   // medicines = trackingFragmentBinding.checkbox.isChecked
                    val userDetail = UserDetail(
                        0,
                        gettime,
                        getdate,
                        getmeasurements,
                        unit_selected,
                        getnotes,
                        medicines, blood_sugar_Selected
                    )
                    mViewModel.insert(userDetail)
                    withContext(Dispatchers.Main){
                        Navigation.findNavController(view).navigate(R.id.history_fragment)
                    }
                }


        }
    }

    override fun onResume() {
        super.onResume()


        addRecyclerviewMeasurements()


        mViewModel.gettimelivedata.observe(viewLifecycleOwner, {

            trackingfragmentBinding.etTime.text = it

        })

        mViewModel.getdatelivedata.observe(viewLifecycleOwner, {

            trackingfragmentBinding.etDate.text = it

        })

        mViewModel.getselectedmeasurementlivedata.observe(viewLifecycleOwner, {

            trackingfragmentBinding.etMeasurement.text = it

        })


    }


    fun populatemmolunits() {
        for (values in 1..600) {
            measurements.add(values.toString())
        }
    }

    fun populatemgunits() {

        var myFloat: Float = 0.0F
        while (myFloat < 35) {
            myFloat += 0.1F
            val formattedString = java.lang.String.format("%.01f", myFloat)
            measurements.add(formattedString)
        }

    }


    fun setHorizontalAdapter(measurements: ArrayList<String>) {

        trackingfragmentBinding.rvHorizontalPicker.adapter = sliderAdapter.apply {
            setData(measurements)
            callback = object : PickerAdapter.Callback_interface {
                override fun onItemClicked(view: View) {

                    blood_sugar_Selected = trackingfragmentBinding.rvHorizontalPicker.getChildLayoutPosition(view)
                    trackingfragmentBinding.rvHorizontalPicker.smoothScrollToPosition(blood_sugar_Selected)

                }
            }
        }

    }

    fun setLayoutManager() {

        trackingfragmentBinding.rvHorizontalPicker.layoutManager =
            PickerLayoutManager(requireContext()).apply {
                callback = object : PickerLayoutManager.OnItemSelectedListener {
                    override fun onItemSelected(layoutPosition: Int) {

                        sliderAdapter.setSelectedItem(layoutPosition)
                        blood_sugar_Selected = measurements[layoutPosition].toInt()



                    }
                }
            }


    }



}




