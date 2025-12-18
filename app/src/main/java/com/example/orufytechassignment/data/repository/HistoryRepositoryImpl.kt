package com.example.orufytechassignment.data.repository

import com.example.orufytechassignment.data.remote.HistoryApi
import com.example.orufytechassignment.data.remote.UploadHistoryRequest
import com.example.orufytechassignment.domain.repository.HistoryRepository
import javax.inject.Inject

class HistoryRepositoryImpl @Inject constructor(
    private val api: HistoryApi
) : HistoryRepository {

    override suspend fun uploadHistory(urls: List<String>) {
        api.uploadHistory(
            UploadHistoryRequest(urls)
        )
    }
}
