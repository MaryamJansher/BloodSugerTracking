package com.example.bloodsugartracking9d.koincomponents

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.example.bloodsugartracking9d.room.UserDetail

class ViewmodelKoin(val repositoryKoin: RepositoryKoin) : ViewModel() {


    fun getuserdatalistwithmeasurements(measurement : String) = repositoryKoin.getuserdatalistwithmeasurements(measurement)
    fun getalldata():LiveData<List<UserDetail>> {
     return   repositoryKoin.getalldata()
    }
     fun insert(userDetail: UserDetail) = repositoryKoin.insert(userDetail)
     fun delete(userDetail: UserDetail) = repositoryKoin.delete(userDetail)
    fun set_unit_selected(unit: String) = repositoryKoin.set_unit_selected(unit)
    var get_unit_selected = repositoryKoin.get_unit_selected
    fun showDatePicker(context: Context) = repositoryKoin.showDatePicker(context)
    fun showTimePicker(context: Context) = repositoryKoin.showTimePicker(context)
    fun showMeasurementDialog(activity: Activity) = repositoryKoin.showMeasurementDialog(activity)
    var  gettimelivedata = repositoryKoin.gettimelivedata
    var  getdatelivedata = repositoryKoin.getdatelivedata
    var  getselectedmeasurementlivedata = repositoryKoin.getselectedmeasurementlivedata





}