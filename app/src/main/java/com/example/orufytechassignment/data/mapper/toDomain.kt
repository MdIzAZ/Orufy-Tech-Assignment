package com.example.orufytechassignment.data.mapper

import com.example.orufytechassignment.data.local.entity.VisitedUrlEntity
import com.example.orufytechassignment.domain.models.VisitedUrl

fun VisitedUrlEntity.toDomain(): VisitedUrl {
    return VisitedUrl(
        id = id,
        url = url,
        timestamp = timestamp
    )
}
