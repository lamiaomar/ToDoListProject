package com.example.todolistproject.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class TaskViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    private val _title = MutableLiveData<String>()
    val title : LiveData<String>
         get() = _title

    private val _description = MutableLiveData<String>()
    val description : LiveData<String>
        get() = _description

    private val _dueDate = MutableLiveData<String>()
    val dueDate : LiveData<String>
        get() = _dueDate

//    private val _state = MutableLiveData<Boolean>()
//    val state : LiveData<Boolean>
//        get() = _state


    fun addItemToList(item: Task) {
        alltasks.add(item)
    }

    fun showData (){
        val item = alltasks[_currentPosition.value!!]

        _title.value = item.title
        _description.value = item.description
        _dueDate.value = item.dueDate

    }

    fun deleteFromList(){
        val item = alltasks[_currentPosition.value!!]
        alltasks.remove(item)
    }




}