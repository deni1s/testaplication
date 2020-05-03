package com.example.storage.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "links_table")
data class LinkSM(
    @PrimaryKey
    var link: String,
    var type: String
)
