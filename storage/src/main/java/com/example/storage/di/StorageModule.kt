package com.example.storage.di

import android.app.Application
import androidx.room.Room
import com.example.storage.AppDatabase
import com.example.storage.DATABASE_NAME
import com.example.storage.links.LinkDao
import com.example.storage.news.NewsDao
import dagger.Module
import dagger.Provides

@Module
class StorageModule {

    @Provides
    fun provideRoom(context: Application): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME).build()
    }

    @Provides
    fun provideLinkPersistence(database: AppDatabase): LinkDao {
        return database.linkPersistence()
    }

    @Provides
    fun provideNewsPersistence(database: AppDatabase): NewsDao {
        return database.newsPersistence()
    }
}