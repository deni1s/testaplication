package com.example.entity.news

import android.util.Log
import com.example.entity.Link
import com.example.entity.News
import com.example.entity.Result
import com.example.repository.links.LinkRepository
import com.example.repository.news.NewsRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import kotlin.Exception

internal class NewsModelImpl(
    val newsRepository: NewsRepository,
    val linkRepository: LinkRepository
) : NewsModel {

    private var linkList: List<Link> = emptyList()
    private var lastLoadedLinkPosition: Int = 0

    override suspend fun loadNewsList(): Flow<Result<List<News>>> {
        return linkRepository.getLinkList().map {
            linkList = it
            lastLoadedLinkPosition = 0
            loadNewsList(lastLoadedLinkPosition)
        }
    }

    override suspend fun loadNextNewsListPart(): Result<List<News>> {
        lastLoadedLinkPosition++
        return loadNewsList(lastLoadedLinkPosition)
    }

    private suspend fun loadNewsList(currentListPosition: Int): Result<List<News>> {
        return if (lastLoadedLinkPosition >= linkList.size) {
            Result.Success(emptyList())
        } else {
            try {
                newsRepository.loadNewsList(linkList[currentListPosition])
            } catch (e: Exception) {
                Log.e("news list loading error", e.localizedMessage)
                Result.Error(e)
            }
        }
    }

    override suspend fun addNewsItemRead(news: News) {
        if (!news.isNewsWatched) {
            newsRepository.addToWatchedNewsList(news)
        }
    }
}

