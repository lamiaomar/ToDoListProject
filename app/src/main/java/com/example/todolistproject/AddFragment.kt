package com.example.todolistproject

import android.app.DatePickerDialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentAddBinding
import com.example.todolistproject.model.DatePickerFragment
import com.example.todolistproject.model.TaskViewModel
import java.text.SimpleDateFormat
import java.util.*


class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

//    private var _viewBinding : FragmentAddBinding? = null
//    private val viewBinding get() = _viewBinding!!

    private val sharedViewModel: TaskViewModel by activityViewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentAddBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

 //       _binding = FragmentAddBinding.bind(view)

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addFragment= this@AddFragment
        }

        //Calender
        _binding?.apply {
            dueDateButton.setOnClickListener {
                Toast.makeText(context, "dueDateButton", Toast.LENGTH_SHORT).show()
                //create the object
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager


                datePickerFragment.show(parentFragmentManager,"REQUEST_CODE")


                supportFragmentManager?.setFragmentResultListener(
                    "REQUEST_CODE" , viewLifecycleOwner){
                        resultKey , bundle ->

                    if (resultKey == "REQUEST_CODE"){
                        val date = bundle.getString("SELECTED_DATE")
                        calenderText.text = date
                    }

                }
            }
        }

    }

    fun setTask() {
        sharedViewModel.addItemToList()
        findNavController().navigate(R.id.action_addFragment_to_toDoListFragment)
    }
}