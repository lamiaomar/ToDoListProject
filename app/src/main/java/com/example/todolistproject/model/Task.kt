package com.example.todolistproject.model

import android.widget.CheckBox

data class Task(
    val title : String ,
    val description : String,
    val dueDate :String ,
    val state : Boolean
)
