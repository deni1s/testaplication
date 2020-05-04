package com.example.repository.mappers

import com.example.data.response.NewsJsonResponse
import com.example.data.response.NewsXmlResponse
import com.example.entity.News
import com.example.repository.extension.requireField
import com.example.storage.entity.NewsSM
import org.jsoup.Jsoup
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun NewsXmlResponse.toDomainModel(storedList: List<NewsSM>): List<News> {
    return getNewsList()?.map {
        with(it) {
            val title = title.requireField()
            News(
                title = title,
                description = description.requireField(),
                isNewsWatched = storedList.containsTitle(title),
                publishedAt = LocalDateTime.parse(pubDate, LOCAL_DATE_SERVER_XML_FORMAT),
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
                description = description ?: content,
                isNewsWatched = storedList.containsTitle(title),
                publishedAt = LocalDateTime.parse(publishedAt, LOCAL_DATE_SERVER_JSON_FORMAT),
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

private val LOCAL_DATE_SERVER_XML_FORMAT = DateTimeFormatter.RFC_1123_DATE_TIME
private val LOCAL_DATE_SERVER_JSON_FORMAT = DateTimeFormatter.ISO_OFFSET_DATE_TIME
