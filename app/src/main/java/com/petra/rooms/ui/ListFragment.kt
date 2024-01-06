package com.petra.rooms.ui

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.room.Room
import com.petra.rooms.R
import com.petra.rooms.data.TaskDao
import com.petra.rooms.data.TaskDatabase
import com.petra.rooms.databinding.FragmentListBinding
import com.petra.rooms.models.TaskItem


class ListFragment : Fragment() {
    private var _binding: FragmentListBinding? = null
    private val binding get() = _binding!!
    private lateinit var DB: TaskDatabase

    private lateinit var TaskList: List<TaskItem>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentListBinding.inflate(inflater, container, false)

        DB = Room.databaseBuilder(
            requireContext(),
            TaskDatabase::class.java,
            "task_database"
        ).fallbackToDestructiveMigration().allowMainThreadQueries().build()

        binding.newTaskButton.setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToAddTaskFragment()
            findNavController().navigate(action)
        }

        TaskList = DB.dao().getTask()

        val recycler: RecyclerView = binding.recycler
        recycler.layoutManager = LinearLayoutManager(requireContext(), RecyclerView.VERTICAL, false)

        // Put the ArrayList From the Adapter to the RecyclerView
        val adapter = TaskAdapter(TaskList)
        recycler.adapter = adapter

//        Log.d("GET_DATA", DB.dao().getTask().toString())

        return binding.root
    }
}