package com.example.bloodsugartracking9d.koincomponents

import android.app.Activity
import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.example.bloodsugartracking9d.CommonFunction
import com.example.bloodsugartracking9d.prefrences.TinyDB

import com.example.bloodsugartracking9d.room.UserDao
import com.example.bloodsugartracking9d.room.UserDetail

class RepositoryKoin(val userDao: UserDao, val tinyDb: TinyDB , val commonFunction: CommonFunction) {

     var unit_selected :  MutableLiveData<String> = MutableLiveData<String>()





    fun getalldata():LiveData<List<UserDetail>> {
     return   userDao.getuserdatalist()
    }
    fun getuserdatalistwithmeasurements(measurement : String) = userDao.getuserdatalistwithmeasurements(measurement)
    fun insert(userDetail: UserDetail) = userDao.insert(userDetail)
    fun delete(userDetail: UserDetail) = userDao.delete(userDetail)
    fun save_time(time: String) = tinyDb.putString(com.example.bloodsugartracking9d.save_time, time)
    fun save_date(date: String) = tinyDb.putString(com.example.bloodsugartracking9d.save_date, date)

    fun set_unit_selected(unit: String): MutableLiveData<String> {
        unit_selected.value = unit

        return unit_selected

    }

    var get_unit_selected = unit_selected
    fun showDatePicker(context: Context) = commonFunction.showDatePicker(context)
    fun showTimePicker(context: Context) = commonFunction.showTimePicker(context)
    fun showMeasurementDialog(activity: Activity) = commonFunction.showMeasurementDialog(activity)
    var  gettimelivedata = commonFunction.gettimelivedata
     var getdatelivedata = commonFunction.getdatelivedata
    var getselectedmeasurementlivedata = commonFunction.getselectedmeasurementlivedata











}