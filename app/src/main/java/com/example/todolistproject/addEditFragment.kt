package com.example.todolistproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentAddEditBinding
import com.example.todolistproject.model.Task
import com.example.todolistproject.model.TaskViewModel
import com.example.todolistproject.model.alltasks


class addEditFragment : Fragment() {

    private var _binding : FragmentAddEditBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentAddEditBinding.inflate(inflater,container,false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.button.setOnClickListener{
            findNavController().navigate(R.id.action_addEditFragment_to_toDoListFragment)
            setTask()
        }

        binding.deleteButton.setOnClickListener {
            sharedViewModel.deleteFromList()
            findNavController().navigate(R.id.action_addEditFragment_to_toDoListFragment)

        }
    }




    fun getTitleOfTask () : String{
        val titleName = binding.textEditTitle.text.toString()
        return titleName
    }

    fun getTaskDescription () : String{
        val titleDescription = binding.textEditDescription.text.toString()
        return titleDescription
    }

//    fun getStatueTask() : CheckBox {
//        val TaskStatte = binding.state
//        return TaskStatte
//    }

    fun setTask(){
        var item = Task(getTitleOfTask() , getTaskDescription() , "vv" , true )
        sharedViewModel.addItemToList(item)
    }




}