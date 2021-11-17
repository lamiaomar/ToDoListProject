package com.example.todolistproject.model

var alltasks: MutableList<Task> =
    mutableListOf(
        Task("Study","study Math", "02-12-2020", true),
        Task("Shopping","Go to the mall", "09-2-2021", true),
        Task("Work","1- prepare for the meeting" +
                                  "2- present" +
                                  "3- work with the team"  , "21-7-2021", true)

    )