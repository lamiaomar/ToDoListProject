package com.example.todolistproject.model

import android.annotation.SuppressLint
import android.app.DatePickerDialog
import android.provider.Settings.Global.getString
import android.widget.Toast
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.databinding.adapters.TextViewBindingAdapter.setText
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.findNavController
import com.example.todolistproject.R
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.*

/**
 * [TaskViewModel] holds information about a TASKS in TO DO LIST in terms of title, description,due date
 * , creation date and state.
 * It also knows how to ADD , EDIT , DELETE task from the list.
 *
 */
class TaskViewModel : ViewModel() {

    private var _currentPosition = MutableLiveData<Int>()
    val currentPosition
        get() = _currentPosition

    var title = MutableLiveData<String?>()

    var description = MutableLiveData<String?>()

    var dueDate = MutableLiveData<String?>()

    var creationDate = MutableLiveData<String>()

    var state = MutableLiveData<Boolean>()

    var passNum = MutableLiveData<String?>("")

    var ispassOrNot = MutableLiveData<Boolean>(true)

    private var _deleteDialog = 0
    val delteDialog: Int
        get() = _deleteDialog



    /**
     * Add data to [alltasks] list
     */
    fun addItemToList() {

        calculateHMDayLeft(dueDate.value!!)

        var item = Task(
            title.value!!,
            description.value!!,
            dueDate.value!!,
            false,
            R.drawable.ic_baseline_radio_button_unchecked_24,
            creationDate.value!!,
            passNum.value!!
        )
        alltasks.add(item)

        resetVlues()

    }


    /**
     * To Display data in VIEW fragment or EDIT fragment
     */
    fun showData() {
        val item = alltasks[_currentPosition.value!!]

        title.value = item.title
        description.value = item.description
        dueDate.value = item.dueDate
        state.value = item.state
        calculateHMDayLeft(dueDate.value!!)


    }


    /**
     * Delete the task
     */
    fun deleteFromList() {
        val item = alltasks[_currentPosition.value!!]
        alltasks.remove(item)
    }


    /**
     * Updates the task based on the user changes
     */
    fun editInList() {

        var item = Task(
            title.value!!, description.value!!, dueDate.value!!, state.value!!,
            R.drawable.notcompleted,
            creationDate.value!!, passNum.value!!
        )

        alltasks.add(item)

        deleteFromList()
    }


    @SuppressLint("SimpleDateFormat")
    fun setCreationDay() {
        val sdf = SimpleDateFormat("yyyy-MM-dd")
        val currentDate1 = sdf.format(Date())


        creationDate.value = currentDate1
    }


    /**
     * Convert the due date from string to date
     * then assign to [ispassOrNot.value] if the due date is AFTER today
     * or assign false
     */
    @SuppressLint("SimpleDateFormat")
    fun calculateHMDayLeft(currentDate: String) {

        try {

            val sdf = SimpleDateFormat("yyyy-MM-dd").parse(currentDate)

            ispassOrNot.value = (sdf.after(Date()))

        } catch (ignored: java.text.ParseException) {

            ispassOrNot.value = false
        }

    }


    /**
     * Check whether the task due date passed or not
     */
    fun checkTaskState() {
        if (ispassOrNot.value!!) {
            passNum.value = "Not Yet"
        } else {
            passNum.value = "PASSED"
        }
    }


    /**
     * Reinitialize Data
     */
    fun resetVlues() {
        title.value = null
        description.value = null
        dueDate.value = null
        state.value = false
        passNum.value = ""
        ispassOrNot.value = true

    }


    /**
     * Returns true if these three values are not equal to null. Returns false otherwise.
     */
    fun preventNullPointerExceptionVM(): Boolean {
        return !(title.value == null || description.value == null
                || dueDate.value == null)
    }

}


