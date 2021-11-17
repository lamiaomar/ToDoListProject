package com.example.todolistproject.model

import android.app.DatePickerDialog
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import java.text.SimpleDateFormat
import java.util.*

class TaskViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    var title = MutableLiveData<String?>()

    var description = MutableLiveData<String?>()

    var dueDate = MutableLiveData<String?>()

    var date = MutableLiveData<Date>()

    var state = MutableLiveData<Boolean>()



    fun addItemToList() {

        var item = Task(title.value!!,description.value!!
                       ,dueDate.value!! ,false)
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
                        ,dueDate.value!! ,state.value!!)

        alltasks.add(item)

        //then delte the item
        deleteFromList()
    }


    fun setState() : Boolean{
        if (state.value!!.equals(true)) return true

        return false
    }



}