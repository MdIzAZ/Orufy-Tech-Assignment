package com.example.orufytechassignment.data.remote

import retrofit2.http.Body
import retrofit2.http.POST

interface HistoryApi {

    @POST("upload")
    suspend fun uploadHistory(
        @Body request: UploadHistoryRequest
    )
}
