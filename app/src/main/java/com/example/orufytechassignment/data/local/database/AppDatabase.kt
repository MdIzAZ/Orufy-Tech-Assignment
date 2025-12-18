package com.example.orufytechassignment.data.local.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.orufytechassignment.data.local.dao.VisitedUrlDao
import com.example.orufytechassignment.data.local.entity.VisitedUrlEntity

@Database(
    entities = [VisitedUrlEntity::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun visitedUrlDao(): VisitedUrlDao
}
