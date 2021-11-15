package com.example.todolistproject.data

import com.example.todolistproject.R
import com.example.todolistproject.model.Task
import com.example.todolistproject.model.alltasks

class dataSource {
    fun loadTask () : MutableList<Task>{
        return alltasks

    }
}