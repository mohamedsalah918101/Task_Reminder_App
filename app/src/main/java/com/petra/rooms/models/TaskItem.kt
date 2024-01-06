package com.petra.rooms.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.ColumnInfo

@Entity(tableName = "task_entity")
data class TaskItem(
    @PrimaryKey(autoGenerate = true) val id:Int = 0,
    @ColumnInfo(name = "task_name") val taskName: String,
    @ColumnInfo(name = "task_description") val taskDescription: String,
    @ColumnInfo(name = "task_date") val taskDate: String,

)

