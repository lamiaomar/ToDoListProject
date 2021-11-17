package com.example.todolistproject.model

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.setFragmentResult
import com.example.todolistproject.R
import java.text.SimpleDateFormat
import java.util.*


class DatePickerFragment : DialogFragment() , DatePickerDialog.OnDateSetListener {

    private val calender = Calendar.getInstance()

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {

        //Default date
        val year = calender.get(Calendar.YEAR)
        val month = calender.get(Calendar.MONTH)
        val day = calender.get(Calendar.DAY_OF_MONTH)

        //return new dialog object
        return DatePickerDialog(requireActivity() ,this ,
                                year , month , day)
    }

    override fun onDateSet(view: DatePicker?, year: Int, month: Int, dayOfMonth: Int) {

        calender.set(Calendar.YEAR,year)
        calender.set(Calendar.MONTH , month)
        calender.set(Calendar.DAY_OF_MONTH,dayOfMonth)

        val selectDate = SimpleDateFormat("dd-MM-yyyy" , Locale.ENGLISH).format(calender.time)

        val selectedDateBundle = Bundle()
        selectedDateBundle.putString("SELECTED_DATE" , selectDate)

        setFragmentResult("REQUEST_CODE" , selectedDateBundle)

    }
}