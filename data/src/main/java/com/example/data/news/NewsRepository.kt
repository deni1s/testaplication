package com.example.data.news

import com.example.data.response.NewsResponse
import com.example.model.Link
import kotlinx.coroutines.Deferred

class NewsRepository(val jsonNewsApiInterface: NewsApiInterface, val rssNewsApiInterface: NewsApiInterface) {

    suspend fun loadNewsList(link: Link): NewsResponse {
        if (link.isJson) {
            return jsonNewsApiInterface.loadJsonNews(link.link).await()
        } else {
            return rssNewsApiInterface.loadRssNews(link.link).await()
        }
    }

    private suspend fun isResponseSuccessfull(resp: Deferred<NewsResponse>): Boolean {
        try {
            return resp.await().isSuccessful()
        } catch (e: java.lang.Exception) {
            return false
        }
    }

    suspend fun checkLinkFromUrl(linkString: String): Link {
        val jsonNewsResponse = jsonNewsApiInterface.loadJsonNews(linkString)
        val rssNewsResponse = rssNewsApiInterface.loadRssNews(linkString)
        val link = Link()
        if (isResponseSuccessfull(jsonNewsResponse)) {
            link.link = linkString
            link.isJson = true
        } else if (isResponseSuccessfull(rssNewsResponse)) {
            link.link = linkString
            link.isJson = false
        }
        return link
    }
}