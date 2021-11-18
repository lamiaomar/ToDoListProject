package com.example.todolistproject

import android.os.Bundle
import android.provider.Settings
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.content.ContentProviderCompat
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.fragment.app.setFragmentResultListener
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentEditIBinding
import com.example.todolistproject.model.DatePickerFragment
import com.example.todolistproject.model.TaskViewModel
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class EditIFragment : Fragment() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    private var _binding : FragmentEditIBinding? = null
    private val binding get() = _binding!!

    private val sharedViewModel: TaskViewModel by activityViewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = DataBindingUtil.inflate(inflater , R.layout.fragment_edit_i , container , false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

//        sharedViewModel.currentPosition.value = taskNum

        binding?.apply {
            lifecycleOwner = viewLifecycleOwner
            viewModel = sharedViewModel
            binding.editFragment = this@EditIFragment


        }

        //Calender
        _binding?.apply {
            button1.setOnClickListener {
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

        sharedViewModel.setCurrentDay()

        //To display data
        sharedViewModel.showData()


//        binding.doneButton.setOnClickListener {
//            changeThestate()
//            Toast.makeText(context, "Data is updated", Toast.LENGTH_SHORT).show()
//            findNavController().navigate(R.id.action_editIFragment_to_viewFragment)
//
//        }

        binding.deleteButton.setOnClickListener {
            showFinalScoreDialog()
        }



    }

    fun displayTheEdit(){
        Toast.makeText(context, "Data is updated", Toast.LENGTH_SHORT).show()
        sharedViewModel.editInList()
        findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)
    }

    fun changeThestate(){
        sharedViewModel.setState()
    }


    private fun showFinalScoreDialog() {
        MaterialAlertDialogBuilder(requireContext())
            .setTitle(getString(R.string.remove))
            .setMessage(getString(R.string.message))
            .setCancelable(false)
            .setNegativeButton(getString(R.string.exit)) { _, _ ->

                findNavController().navigate(R.id.action_editIFragment_self)
            }
            .setPositiveButton(getString(R.string.delete)) { _, _ ->
                sharedViewModel.deleteFromList()
                findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)


            }
            .show()


    }

    fun backtoMainFrag(){

        findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)
    }

}