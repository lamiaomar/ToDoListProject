package com.example.todolistproject.model

import com.example.todolistproject.R

var alltasks: MutableList<Task> =
    mutableListOf(
        Task("Study","study Math", "02-12-2020", true , R.drawable.notcompleted,"11-18-2021" , "PASSED"),
        Task("Shopping","Go to the mall", "09-2-2021", true , R.drawable.completed,"11-18-2021","PASSED"),
        Task("Work","prepare for the meeting" , "21-7-2021", true, R.drawable.notcompleted,"11-19-2021","")

    )