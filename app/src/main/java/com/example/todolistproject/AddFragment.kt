package com.example.todolistproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentAddBinding
import com.example.todolistproject.model.DatePickerFragment
import com.example.todolistproject.model.TaskViewModel
import java.util.*


class AddFragment : Fragment(R.layout.fragment_add) {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TaskViewModel by activityViewModels()


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

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            addFragment = this@AddFragment
        }

        /*
        * To display the Calender dialog
        */
        _binding?.apply {
            dueDateButton.setOnClickListener {

                //create the object
                val datePickerFragment = DatePickerFragment()
                val supportFragmentManager = requireActivity().supportFragmentManager

                datePickerFragment.show(parentFragmentManager, "TAG")


                datePickerFragment?.setFragmentResultListener(
                    "REQUEST_CODE"
                ) {

                        resultKey, bundle ->
                    if (resultKey == "REQUEST_CODE") {
                        val date = bundle.getString("SELECTED_DATE")
                        calenderText.text = date

                    }

                }
            }
        }

        /*
        * Re-initializes the data in the ViewModel
        */
        sharedViewModel.resetVlues()

        /*
        * To set the task creation date
        */
        sharedViewModel.setCreationDay()

    }


    /*
     *Add the task to the list in TaskViewModel,
     * Prevent the user from ADD a task without
     *    TITLE - DESCRIPTION - DUE DATE
     *
     */
    fun setTask() {
        if (preventNullPointerException()) {
            sharedViewModel.addItemToList()
            findNavController().navigate(R.id.action_addFragment_to_toDoListFragment)
        } else {
            Toast.makeText(context, getString(R.string.Youhavetofillallfields), Toast.LENGTH_SHORT)
                .show()
            findNavController().navigate(R.id.action_addFragment_self)
        }
    }


    /*
    * When the user click on cancel button
    */
    fun backtoMainFrag() {

        findNavController().navigate(R.id.action_addFragment_to_toDoListFragment)
    }


    /*
     * Check if the user click add button with empty field
     * in the view model
     */
    fun preventNullPointerException(): Boolean {
        if (sharedViewModel.preventNullPointerExceptionVM())
            return true

        return false
    }
}

