package com.example.todolistproject.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistproject.R
import com.example.todolistproject.model.Task
import com.example.todolistproject.toDoListFragmentDirections

class taskAdapter (
    private val context : Context,
    private val dataset : MutableList<Task>
        ) : RecyclerView.Adapter<taskAdapter.TaskViewHolder>(){


            class TaskViewHolder(internal val view : View) : RecyclerView.ViewHolder(view){

                val nameTask : TextView = view.findViewById(R.id.title_textView)
                val dueDateTask : TextView = view.findViewById(R.id.dueDate_textView)
                val descTask : TextView = view.findViewById(R.id.desc_textView)

            }

    override fun onCreateViewHolder(
        parent: ViewGroup, viewType: Int
    ): TaskViewHolder {

        val adapterLayout = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_shape , parent , false)
        return TaskViewHolder(adapterLayout)

    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {

        val item = dataset[position]

        holder.nameTask.text = item.title
        holder.dueDateTask.text = item.dueDate
        holder.descTask.text = item.description

        holder.nameTask.setOnClickListener {

            val action = toDoListFragmentDirections.actionToDoListFragmentToViewFragment(position)
            holder.nameTask.findNavController().navigate(action)

        }
    }

    override fun getItemCount(): Int = dataset.size

}