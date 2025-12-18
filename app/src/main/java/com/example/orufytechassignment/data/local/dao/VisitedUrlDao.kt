package com.example.orufytechassignment.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.orufytechassignment.data.local.entity.VisitedUrlEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface VisitedUrlDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUrl(entity: VisitedUrlEntity)

    @Query("SELECT * FROM visited_urls ORDER BY timestamp DESC")
    fun getAllUrls(): Flow<List<VisitedUrlEntity>>

    @Query("DELETE FROM visited_urls")
    suspend fun clearAll()
}
