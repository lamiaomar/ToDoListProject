package com.example.todolistproject.model

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData

data class Task(
    val title: String,
    val description: String,
    val dueDate: String,
    val state: Boolean ,
    val imageState : Int ,
    val cretionDate : String ,
    val passDay : String
)
