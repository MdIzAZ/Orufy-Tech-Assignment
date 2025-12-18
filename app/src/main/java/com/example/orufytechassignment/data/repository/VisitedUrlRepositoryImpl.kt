package com.example.orufytechassignment.data.repository

import com.example.orufytechassignment.data.local.dao.VisitedUrlDao
import com.example.orufytechassignment.data.local.entity.VisitedUrlEntity
import com.example.orufytechassignment.data.mapper.toDomain
import com.example.orufytechassignment.domain.models.VisitedUrl
import com.example.orufytechassignment.domain.repository.VisitedUrlRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class VisitedUrlRepositoryImpl @Inject constructor(
    private val dao: VisitedUrlDao
) : VisitedUrlRepository {

    override suspend fun saveUrl(url: String) {
        dao.insertUrl(
            VisitedUrlEntity(
                url = url,
                timestamp = System.currentTimeMillis()
            )
        )
    }

    override fun getHistory(): Flow<List<VisitedUrl>> {
        return dao.getAllUrls().map { list ->
            list.map { it.toDomain() }
        }
    }

    override suspend fun clearHistory() {
        dao.clearAll()
    }
}
