package com.example.repository.mappers

import com.example.data.response.NewsJsonResponse
import com.example.data.response.NewsXmlResponse
import com.example.entity.News
import com.example.repository.extension.requireField
import com.example.storage.entity.NewsSM
import org.jsoup.Jsoup

fun NewsXmlResponse.toDomainModel(storedList: List<NewsSM>): List<News> {
    return getNewsList()?.map {
        with(it) {
            val title = title.requireField()
            News(
                title = title,
                description = description.requireField(),
                isNewsWatched = storedList.containsTitle(title),
                publishedAt = pubDate.requireField(),
                urlToImage = extractImageUrl(description),
                url = link
            )
        }
    } ?: emptyList()
}

fun extractImageUrl(description: String?): String? {
    if (!description.isNullOrEmpty()) {
        val document = Jsoup.parse(description)
        val imgs = document.select("img")
        for (img in imgs) {
            if (img.hasAttr("src")) {
                return img.attr("src")
            }
        }
        return ""
    } else {
        return ""
    }
}

fun NewsJsonResponse.toDomainModel(storedList: List<NewsSM>): List<News> {
    return articles.map {
        with(it) {
            News(
                title = title,
                description = description,
                isNewsWatched = storedList.containsTitle(title),
                publishedAt = publishedAt,
                urlToImage = urlToImage,
                url = url
            )
        }
    }
}

private fun List<NewsSM>.containsTitle(title: String): Boolean {
    forEach {
        if (it.title == title) {
            return true
        }
    }
    return false
}

fun News.toStorageModel(): NewsSM {
    return NewsSM(title = title)
}
