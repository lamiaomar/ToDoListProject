package com.example.todolistproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
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
        val button : Button = view.findViewById(R.id.floating_action_button)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        recyclerView = binding.recyclerView
        recyclerView.adapter = taskAdapter(this.requireContext(), dataset )
        binding.floatingActionButton.setOnClickListener{
            findNavController().navigate(R.id.action_toDoListFragment_to_addEditFragment)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}