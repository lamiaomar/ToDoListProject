package com.example.todolistproject

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.databinding.FragmentEditIBinding
import com.example.todolistproject.model.TaskViewModel


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

        //To display data
        sharedViewModel.showData()

//        binding.doneButton.setOnClickListener{
//            findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)
//        }

        binding.deleteButton.setOnClickListener {
            sharedViewModel.deleteFromList()
           findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)

        }

        binding.doneButton.setOnClickListener {
            changeThestate()
            findNavController().navigate(R.id.action_editIFragment_to_viewFragment)

        }

//        binding.state.setOnCheckedChangeListener { _, isChecked ->
//            sharedViewModel.setState()
//        }
    }

    fun displayTheEdit(){
        sharedViewModel.editInList()
        findNavController().navigate(R.id.action_editIFragment_to_toDoListFragment)
    }

    fun changeThestate(){
        sharedViewModel.setState()
    }

}