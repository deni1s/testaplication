package com.example.entity


data class News(
    val description: String?,
    val title: String,
    val publishedAt: String?,
    val urlToImage: String?,
    val url: String?,
    val isNewsWatched: Boolean
)
