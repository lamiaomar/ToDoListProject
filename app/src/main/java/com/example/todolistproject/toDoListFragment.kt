package com.example.todolistproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.todolistproject.adapter.taskAdapter
import com.example.todolistproject.data.dataSource
import com.example.todolistproject.databinding.FragmentToDoListBinding
import com.example.todolistproject.model.Task
import javax.sql.DataSource


class toDoListFragment : Fragment() {

 private var _binding : FragmentToDoListBinding? = null
 private val binding get() = _binding!!

    private lateinit var recyclerView: RecyclerView

    lateinit var dataset : MutableList<Task>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        dataset = dataSource().loadTask()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentToDoListBinding.inflate(inflater , container , false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recyclerView
        recyclerView.adapter = taskAdapter(this.requireContext(), dataset )
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}