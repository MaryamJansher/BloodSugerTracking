package com.example.bloodsugartracking9d

import android.app.Activity
import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.TimePickerDialog
import android.content.Context

import android.text.format.DateFormat
import android.view.View
import android.widget.*
import androidx.lifecycle.MutableLiveData

import java.util.*

class CommonFunction(var context: Context) {

    private  var getdate: String? = null
    private  var gettime: String? = null

      var gettimelivedata: MutableLiveData<String> = MutableLiveData<String>()
      var getdatelivedata: MutableLiveData<String> = MutableLiveData<String>()
    var getselectedmeasurementlivedata: MutableLiveData<String> = MutableLiveData<String>()

    private var calendar: Calendar = Calendar.getInstance()


    fun showTimePicker(context: Context) {

        val tpd = TimePickerDialog(
            context, { view1: TimePicker?, hourOfDay: Int, minute: Int ->

                gettime = String.format("%02d", hourOfDay) + ":" + String.format("%02d", minute)
                gettimelivedata.value = gettime

            },
            calendar.get(Calendar.HOUR_OF_DAY),
            calendar.get(Calendar.MINUTE),
            DateFormat.is24HourFormat(context)
        )
        tpd.show()




    }


    fun showDatePicker(context: Context) {

        val dpd = DatePickerDialog(
            context,
            { view1: DatePicker?, year: Int, month: Int, dayOfMonth: Int ->

                getdate = String.format("%d", year) + "-" + String.format(
                    "%02d",
                    month + 1
                ) + "-" + String.format("%02d", dayOfMonth)

                getdatelivedata.value = getdate


            },

            calendar.get(Calendar.YEAR),
            calendar.get(Calendar.MONTH),
            calendar.get(Calendar.DATE)
        )


        dpd.show()





    }


    fun showMeasurementDialog(activity: Activity) {

        lateinit var radioButton: RadioButton
        val dialogView: View
        val radioGroup: RadioGroup
        val btnok: Button
        val btn_cancel: Button
        val dialogBuilder = AlertDialog.Builder(activity).create()
        val inflater = activity.layoutInflater
         var getselectedmeasurement: String? = null


        dialogView = inflater.inflate(R.layout.measurement_dialog, null)
        radioGroup = dialogView.findViewById(R.id.radio_group)
        btnok = dialogView.findViewById(R.id.btn_ok)
        btn_cancel = dialogView.findViewById(R.id.btn_cancel)

        btnok.setOnClickListener {

            val selectedId: Int = radioGroup.getCheckedRadioButtonId()
            radioButton = radioGroup.findViewById(selectedId) as RadioButton
            getselectedmeasurement = radioButton.text.toString()
            getselectedmeasurementlivedata.value = getselectedmeasurement
            dialogBuilder.dismiss()
        }

        btn_cancel.setOnClickListener {

            dialogBuilder.dismiss()

        }

        dialogBuilder.setView(dialogView)
        dialogBuilder.setCancelable(false)
        dialogBuilder.show()





    }


}