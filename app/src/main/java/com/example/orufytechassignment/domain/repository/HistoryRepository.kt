package com.example.orufytechassignment.domain.repository

interface HistoryRepository {
    suspend fun uploadHistory(urls: List<String>)
}
