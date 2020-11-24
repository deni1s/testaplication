package com.example.entity.news

import com.example.entity.News
import com.example.entity.Result
import kotlinx.coroutines.flow.Flow

interface NewsModel {
    suspend fun loadNewsList(): Flow<Result<List<News>>>
    suspend fun loadNextNewsListPart(): Result<List<News>>
    suspend fun addNewsItemRead(news: News)
}
