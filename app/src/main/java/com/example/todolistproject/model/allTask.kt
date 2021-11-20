package com.example.todolistproject.model

import com.example.todolistproject.R

var alltasks: MutableList<Task> =
    mutableListOf(
        Task("Study","1- Study Math\n"+"2- Do the homeworks\n"+"3- prepare the presentation\n", "21-12-2021", true , R.drawable.notcompleted,"11-18-2021" , "PASSED"),
        Task("Shopping","Go to the mall at 4 pm", "08-10-2021", false , R.drawable.completed,"11-18-2021","PASSED"),
        Task("Work","Prepare for the meeting" , "30-11-2021", true, R.drawable.notcompleted,"11-19-2021","")
    )
