package com.example.data.news

import com.example.data.response.NewsResponse
import com.example.model.Link
import kotlinx.coroutines.Deferred

class NewsRepository(val jsonNewsApiInterface: NewsApiInterface, val rssNewsApiInterface: NewsApiInterface) {

    suspend fun loadNewsList(link: Link): NewsResponse {
        if (link.type == Link.JSON_TYPE) {
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

    suspend fun getLinkTypeOrReturnInvalid(linkString: String): Int {
        val jsonNewsResponse = jsonNewsApiInterface.loadJsonNews(linkString)
        val rssNewsResponse = rssNewsApiInterface.loadRssNews(linkString)
        if (isResponseSuccessfull(jsonNewsResponse)) {
            return Link.JSON_TYPE
        } else if (isResponseSuccessfull(rssNewsResponse)) {
            return Link.XML_TYPE
        } else {
            return Link.NOT_VALID_TYPE
        }
    }
}