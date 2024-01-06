package com.petra.rooms.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.petra.rooms.R
import com.petra.rooms.models.TaskItem

class TaskAdapter(val itemList: List<TaskItem>) : RecyclerView.Adapter<TaskAdapter.ViewHolder>()
{
     class ViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
         val taskName:TextView = itemView.findViewById(R.id.RtaskTitle)
         val taskDescription:TextView = itemView.findViewById(R.id.RtaskDescription)
         val taskDate:TextView = itemView.findViewById(R.id.RtaskDate)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_design, parent,false)
        )
    }

    override fun getItemCount(): Int {
        return itemList.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val currentItem = itemList[position]
        holder.taskName.text = currentItem.taskName
        holder.taskDescription.text = currentItem.taskDescription
        holder.taskDate.text = currentItem.taskDate.split(" ")[0]

        holder.itemView.setOnClickListener{
            val id = itemList[position].id
            val action = ListFragmentDirections.actionListFragmentToDetailsFragment(id)
            it.findNavController().navigate(action)
        }
    }
}