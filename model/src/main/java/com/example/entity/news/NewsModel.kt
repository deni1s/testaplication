package com.example.entity.news

import com.example.entity.Link
import com.example.entity.News
import com.example.entity.Result

interface NewsModel {
    suspend fun loadNewsList(): Result<List<News>>
    suspend fun loadNextNewsListPart(): Result<List<News>>
    suspend fun addNewsItemRead(news: News)
    suspend fun getLinkList(): List<Link>
}
