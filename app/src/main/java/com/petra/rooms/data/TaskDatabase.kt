package com.petra.rooms.data

import androidx.room.Database
import androidx.room.RoomDatabase
import com.petra.rooms.models.TaskItem

@Database(entities = [TaskItem::class], version = 1, exportSchema = false)
abstract class TaskDatabase: RoomDatabase() {
    abstract fun dao():TaskDao
}