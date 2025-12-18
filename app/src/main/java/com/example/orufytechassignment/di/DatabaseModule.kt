package com.example.orufytechassignment.di

import android.content.Context
import androidx.room.Room
import com.example.orufytechassignment.data.local.dao.VisitedUrlDao
import com.example.orufytechassignment.data.local.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "orufy_app_db"
        ).build()
    }

    @Provides
    fun provideVisitedUrlDao(
        database: AppDatabase
    ): VisitedUrlDao {
        return database.visitedUrlDao()
    }
}
