package com.example.entity

import org.threeten.bp.LocalDateTime

data class News(
    val description: String,
    val title: String,
    val publishedAt: LocalDateTime,
    val urlToImage: String?,
    val url: String?,
    val isNewsWatched: Boolean
)
