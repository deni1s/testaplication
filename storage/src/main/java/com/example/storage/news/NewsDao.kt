package com.example.storage.news

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.entity.NewsSM

@Dao
interface NewsDao {
    @Query("SELECT * FROM news_table")
    suspend fun getNewsList(): List<NewsSM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addNewsToRead(news: NewsSM)
}
