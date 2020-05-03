package com.example.storage.di

import androidx.room.Room
import com.example.storage.AppDatabase
import com.example.storage.DATABASE_NAME
import com.example.storage.links.LinkDao
import com.example.storage.news.NewsDao
import org.koin.dsl.module.module

val storageModule = module {
    single { Room.databaseBuilder(get(), AppDatabase::class.java, DATABASE_NAME).build() }
    single { get<AppDatabase>().linkPersistence()  }
    single { get<AppDatabase>().newsPersistence()  }
}
