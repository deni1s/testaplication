package com.example.repository.news

import com.example.entity.Link
import com.example.entity.News
import com.example.entity.Result

interface NewsRepository {
    suspend fun loadNewsList(link: Link): Result<List<News>>
    suspend fun loanJsonNews(linkUrl: String): Result<List<News>>
    suspend fun loanXmlNews(linkUrl: String): Result<List<News>>
    suspend fun addToWatchedNewsList(news: News)
}
