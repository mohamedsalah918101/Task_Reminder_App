package com.petra.rooms.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.room.Room
import com.petra.rooms.data.TaskDatabase
import com.petra.rooms.databinding.FragmentDetailsBinding
import com.petra.rooms.models.TaskItem


class DetailsFragment : Fragment() {
    private var _binding:FragmentDetailsBinding ? =null
    private val binding get() = _binding!!

    private lateinit var DB: TaskDatabase

    private val args: DetailsFragmentArgs by navArgs()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentDetailsBinding.inflate(inflater,container,false)

        val id = args.id

        DB = Room.databaseBuilder(
            requireContext(),
            TaskDatabase::class.java,
            "task_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

         val item: List<TaskItem> = DB.dao().getTaskById(id)
         binding.workTrip.text = item[0].taskName
         binding.destination.text = item[0].taskDescription
         binding.workDate.text = item[0].taskDate

        binding.editTaskButton.setOnClickListener{
            val action = DetailsFragmentDirections.actionDetailsFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }

        return binding.root
    }

}