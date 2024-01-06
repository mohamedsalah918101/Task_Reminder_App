package com.petra.rooms.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.navigation.fragment.findNavController
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.android.material.datepicker.MaterialDatePicker
import com.petra.rooms.data.TaskDatabase
import com.petra.rooms.databinding.FragmentAddTaskBinding
import com.petra.rooms.models.TaskItem


class AddTaskFragment : Fragment() {
    private var _binding: FragmentAddTaskBinding? = null
    private val binding get() = _binding!!
    var flag = false
    var dateTask = ""

    private lateinit var DB:TaskDatabase

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentAddTaskBinding.inflate(inflater, container, false)

        val datePicker = MaterialDatePicker.Builder.datePicker().build()

        DB = Room.databaseBuilder(
            requireContext(),
            TaskDatabase::class.java,
            "task_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()
        // for the icon
        binding.chooseDate.setOnClickListener{
            if (!flag) {
                datePicker.apply {
                    show(
                        this@AddTaskFragment.requireActivity().supportFragmentManager,
                        "date_picker"
                    )
                    flag = true

                    addOnPositiveButtonClickListener {
                        dateTask = this.headerText.toString()
                        binding.dateTask.setText(dateTask)
                        flag=false
                    }
                    addOnNegativeButtonClickListener {
                        flag=false
                        Log.d("TAGClose","TAGClose")
                    }
                    addOnCancelListener() {
                        flag=false
                        Log.d("TAGClose","TAGCancel")
                    }

                }
            }
        }
        // For the EditText
        binding.dateTask.setOnClickListener {
            if (!flag) {
                datePicker.apply {
                    show(
                        this@AddTaskFragment.requireActivity().supportFragmentManager,
                        "date_picker"
                    )
                    flag = true

                    addOnPositiveButtonClickListener {
                        dateTask = this.headerText.toString()
                        binding.dateTask.setText(dateTask)
                        flag=false
                    }
                    addOnNegativeButtonClickListener {
                        flag=false
                        Log.d("TAGClose","TAGClose")
                    }
                    addOnCancelListener() {
                        flag=false
                        Log.d("TAGClose","TAGCancel")
                    }

                }
            }
        }
        // Button Save
        binding.apply {
            binding.btnSave.setOnClickListener() {
                if (inputTaskEditText.text.toString()
                        .isNotEmpty() && detailsEditText.text.toString()
                        .isNotEmpty() && dateTask.text.toString().isNotEmpty()
                ) {
                    val taskEntity = TaskItem(
                        0,
                        binding.inputTaskEditText.text.toString(),
                        binding.detailsEditText.text.toString(),
                        binding.dateTask.text.toString()
                    )
                    DB.dao().insertItem(taskEntity)

                    val action = AddTaskFragmentDirections.actionAddTaskFragmentToListFragment()
                    findNavController().navigate(action)
                }
                else
                {
                    Toast.makeText(requireContext(), "Try Again", Toast.LENGTH_SHORT).show()
                }
            }
        }



        return binding.root
    }

}


