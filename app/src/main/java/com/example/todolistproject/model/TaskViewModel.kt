package com.example.todolistproject.model

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.todolistproject.R
import java.text.SimpleDateFormat
import java.util.*

class TaskViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    var title = MutableLiveData<String?>()

    var description = MutableLiveData<String?>()

    var dueDate = MutableLiveData<String?>()

    var currentDate = MutableLiveData<String>()

    var state = MutableLiveData<Boolean>()



    fun addItemToList() {

        var item = Task(title.value!!,description.value!!
                       ,dueDate.value!! ,false, R.drawable.notcompleted)
        alltasks.add(item)

        /*
        set the value to NULL ,
        when I visit ( AddFargment )
         agin no value will displayed
         */
        title.value = null
        description.value = null
        dueDate.value = null
        state.value = false


    }

    fun showData (){
        val item = alltasks[_currentPosition.value!!]

        title.value = item.title
        description.value = item.description.toString()
        dueDate.value = item.dueDate.toString()
        state.value = item.state

    }

    fun deleteFromList(){
        val item = alltasks[_currentPosition.value!!]
        alltasks.remove(item)
    }

    fun editInList(){
        //Add the item in the task as new item
        var item = Task(title.value!!,description.value!!
                        ,dueDate.value!! ,state.value!!, R.drawable.notcompleted)

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
        val sdf = SimpleDateFormat("dd/M/yyyy")
        val currentDate1 = sdf.format(Date())

        currentDate.value = currentDate1 }





}