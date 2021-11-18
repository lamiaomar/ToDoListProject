package com.example.todolistproject

import android.graphics.Paint
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentViewBinding
import com.example.todolistproject.model.DatePickerFragment
import com.example.todolistproject.model.Task
import com.example.todolistproject.model.alltasks
import com.example.todolistproject.model.TaskViewModel

private const val position = "title"

class ViewFragment : Fragment() {

    private lateinit var binding: FragmentViewBinding

    private val sharedViewModel: TaskViewModel by activityViewModels()

    private var taskNum: Int? = 0


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            taskNum = it.getInt(position)
        }
    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        binding = DataBindingUtil.inflate(inflater , R.layout.fragment_view , container , false)

        return binding.root

    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        sharedViewModel.currentPosition.value = taskNum

        //Live data
        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
        }

        //To display data
        sharedViewModel.showData()
        setTheStateInView()



        binding.editButton.setOnClickListener {
//            sharedViewModel.showData()
            findNavController().navigate(R.id.action_viewFragment_to_editIFragment)

        }

        binding.button1.setOnClickListener{
            backtoMainFrag()
        }

        binding.doneButton.setOnClickListener{
            backtoMainFrag()
        }

        sharedViewModel.setCurrentDay()

        //To assign the task is passed or no

        sharedViewModel.assignTheDate()

        //To calculate the num of day past
        sharedViewModel.calculateHMDayLeft(sharedViewModel.dueDate.value!!)

    }

    fun setTheStateInView(){
        if (sharedViewModel.setState())
            binding.viewIsCompleted.text = "DONE"
            binding.viewIsCompleted.setPaintFlags(
            binding.viewIsCompleted.getPaintFlags() or
                    Paint.STRIKE_THRU_TEXT_FLAG)


    }


    fun backtoMainFrag(){
        findNavController().navigate(R.id.action_viewFragment_to_toDoListFragment)

    }



}

