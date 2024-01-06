package com.petra.rooms.data

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.petra.rooms.models.TaskItem

@Dao
interface TaskDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertItem(taskItem: TaskItem)

    @Query("select * from task_entity order by task_date")
    fun getTask(): List<TaskItem>

    @Query("select * from task_entity where id = :id")
    fun getTaskById(id:Int): List<TaskItem>
}