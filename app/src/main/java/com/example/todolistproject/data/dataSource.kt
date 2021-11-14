package com.example.todolistproject.data

import com.example.todolistproject.R
import com.example.todolistproject.model.Task

class dataSource {
    fun loadTask () : MutableList<Task>{
        return mutableListOf<Task>(
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate),
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate),
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate),
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate),
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate),
            Task(R.string.Title , R.string.Description , R.string.app_name , R.string.DueDate)
            )
    }
}