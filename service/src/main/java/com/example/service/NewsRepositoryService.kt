package com.example.service

import com.example.data.news.NewsRepository
import com.example.data.realm.NewsDatabaseInterface
import com.example.data.realm.NewsDatabaseRepository
import com.example.model.Link
import com.example.model.News
import org.koin.standalone.KoinComponent

class NewsRepositoryService(val newsDatabaseRepository: NewsDatabaseInterface, val newsApiRepository: NewsRepository) :
    KoinComponent {

    suspend fun loadNewsList(link: Link): Result<List<News>> {
        try {
            val newsResponse = newsApiRepository.loadNewsList(link)
            val newsList = newsResponse.getNewsList()
            markWatchedNews(newsList!!)
            return Result.Success(newsList)
        } catch (e: Exception) {
            // Catch http errors
            return Result.Error(e)
        } catch (e: Throwable) {
            return Result.Error(e)
        }
    }

    fun markWatchedNews(newsList: List<News>) {
        newsList.forEach({
            if (newsDatabaseRepository.isNewsWasWatched(it)) {
                it.isNewsWatched = true
            }
        })
    }
}