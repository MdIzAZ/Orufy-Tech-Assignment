package com.example.orufytechassignment.domain.repository

import com.example.orufytechassignment.domain.models.VisitedUrl
import kotlinx.coroutines.flow.Flow

interface VisitedUrlRepository {

    suspend fun saveUrl(url: String)

    fun getHistory(): Flow<List<VisitedUrl>>

    suspend fun clearHistory()
}
