package com.example.todolistproject.model

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.provider.Settings.Global.getString
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistproject.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

class TaskViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    var title = MutableLiveData<String?>()

    var description = MutableLiveData<String?>()

    var dueDate = MutableLiveData<String?>()

    var creationDate = MutableLiveData<String>()

    var state = MutableLiveData<Boolean>()

    var passNum = MutableLiveData<String?>("")

    var ispassOrNot = MutableLiveData<Boolean>(true)

    private var _deleteDialog = 0
    val delteDialog: Int
        get() = _deleteDialog



    fun addItemToList() {

        calculateHMDayLeft(dueDate.value!!)
        /*
       set the value to NULL ,
       when I visit ( AddFargment )
        agin no value will displayed
        */

        var item = Task(title.value!!,description.value!!
                       ,dueDate.value!! ,false, R.drawable.notcompleted
                        ,creationDate.value!! , passNum.value!!)
        alltasks.add(item)

        resetVlues()

    }

    fun showData (){
        val item = alltasks[_currentPosition.value!!]

        title.value = item.title
        description.value = item.description.toString()
        dueDate.value = item.dueDate.toString()
        state.value = item.state
        calculateHMDayLeft(dueDate.value!!)


    }

    fun deleteFromList(){
        val item = alltasks[_currentPosition.value!!]
        alltasks.remove(item)
    }

    fun editInList(){
        //Add the item in the task as new item
        var item = Task(title.value!!,description.value!!
                        ,dueDate.value!! ,state.value!!,
                        R.drawable.notcompleted,
                        creationDate.value!! ,passNum.value!!)

        alltasks.add(item)

        //then delte the item
        deleteFromList()
    }


    fun setState() : Boolean{
        if (state.value!!.equals(true)) return true

        return false
    }

//    fun setDate(){
//        dueDate.value =
//    }

    @SuppressLint("SimpleDateFormat")
    fun setCurrentDay() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate1 = sdf.format(Date())


        creationDate.value = currentDate1 }



    @SuppressLint("SimpleDateFormat")
    fun calculateHMDayLeft(currentDate : String){

        try {

            val sdf = SimpleDateFormat("yyyy-MM-dd").parse(currentDate)

            ispassOrNot.value = (sdf.after(Date()))

        }catch (ignored : java.text.ParseException){

            ispassOrNot.value = false
        }

    }


    fun assignTheDate(){
        if (ispassOrNot?.value!!){
            passNum.value = "Not Yet"
        }else{
            passNum.value = "PASSED"
        }
    }


    fun resetVlues() {
        title.value = null
        description.value = null
        dueDate.value = null
        state.value = false
        passNum.value = ""
        ispassOrNot.value = true

    }

}


