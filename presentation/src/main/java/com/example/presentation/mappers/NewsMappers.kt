package com.example.presentation.mappers

import com.example.entity.News
import com.example.presentation.entity.NewsUM

fun News.toUiModel(): NewsUM {
    return NewsUM(
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        isNewsWatched = isNewsWatched,
        title = title
    )
}

fun List<News>.toUiModel(): List<NewsUM> {
    return map { it.toUiModel() }
}

fun NewsUM.toDomainModel(): News {
    return News(
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt,
        isNewsWatched = isNewsWatched,
        title = title
    )
}
