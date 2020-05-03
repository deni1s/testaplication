package com.example.data.response

import com.example.data.entity.NewsJsonNM

data class NewsJsonResponse(
    val status: String,
    val totalResults: Int,
    val articles: List<NewsJsonNM>
)
