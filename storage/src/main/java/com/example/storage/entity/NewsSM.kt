package com.example.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "news_table")
data class NewsSM(
    // newsId is optional field for some sources
    @PrimaryKey
    var title: String
)
