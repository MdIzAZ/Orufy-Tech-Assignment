package com.example.orufytechassignment.di

import com.example.orufytechassignment.data.repository.HistoryRepositoryImpl
import com.example.orufytechassignment.data.repository.VisitedUrlRepositoryImpl
import com.example.orufytechassignment.domain.repository.HistoryRepository
import com.example.orufytechassignment.domain.repository.VisitedUrlRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindVisitedUrlRepository(
        impl: VisitedUrlRepositoryImpl
    ): VisitedUrlRepository


    @Binds
    abstract fun bindHistoryRepository(
        impl: HistoryRepositoryImpl
    ): HistoryRepository
}
