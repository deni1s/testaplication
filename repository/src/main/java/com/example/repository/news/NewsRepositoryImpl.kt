package com.example.repository.news

import com.example.data.entity.mapContent
import com.example.data.news.NewsJsonApi
import com.example.data.news.NewsXmlApi
import com.example.entity.Link
import com.example.entity.LinkType
import com.example.entity.News
import com.example.repository.mappers.toDomainModel
import com.example.repository.mappers.toStorageModel
import com.example.storage.news.NewsDao
import com.example.entity.Result
import java.lang.IllegalArgumentException
import javax.inject.Inject

class NewsRepositoryImpl @Inject constructor(
    private val jsonNewsApi: NewsJsonApi,
    private val rssNewsApi: NewsXmlApi,
    private val newsDao: NewsDao
) : NewsRepository {

    override suspend fun loadNewsList(link: Link): Result<List<News>> {
        return when (link.type) {
            LinkType.Json -> loanJsonNews(link.link)
            LinkType.Xml -> loanXmlNews(link.link)
            LinkType.Invalid -> Result.Error(IllegalArgumentException("unknown link type"))
        }
    }

    override suspend fun loanJsonNews(linkUrl: String): Result<List<News>> {
        val newsList = newsDao.getNewsList()
        return jsonNewsApi.loadNews(linkUrl).mapContent { it.toDomainModel(newsList) }
    }

    override suspend fun loanXmlNews(linkUrl: String): Result<List<News>> {
        val newsList = newsDao.getNewsList()
        return rssNewsApi.loadNews(linkUrl).mapContent { it.toDomainModel(newsList) }
    }

    override suspend fun addToWatchedNewsList(news: News) {
        return newsDao.addNewsToRead(news.toStorageModel())
    }
}
