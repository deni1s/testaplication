package com.example.storage

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.storage.entity.LinkSM
import com.example.storage.entity.NewsSM
import com.example.storage.links.LinkDao
import com.example.storage.news.NewsDao
import ru.appkode.tbi.data.storage.db.converters.DatabaseTypeConverters

internal const val DATABASE_VERSION = 1
internal const val DATABASE_NAME = "newsapp_database"

@Database(
    entities = [
        NewsSM::class,
        LinkSM::class
    ],
    version = DATABASE_VERSION
)
@TypeConverters(DatabaseTypeConverters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun newsPersistence(): NewsDao
    abstract fun linkPersistence(): LinkDao
}
