package com.example.presentation.mappers

import com.example.entity.News
import com.example.presentation.entity.NewsUM
import org.threeten.bp.LocalDateTime
import org.threeten.bp.format.DateTimeFormatter

fun News.toUiModel(): NewsUM {
    return NewsUM(
        description = description,
        url = url,
        urlToImage = urlToImage,
        publishedAt = publishedAt.format(UI_DATE_TIME_FORMATTER_DATE_HRS_MIN),
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
        publishedAt = LocalDateTime.parse(publishedAt, UI_DATE_TIME_FORMATTER_DATE_HRS_MIN),
        isNewsWatched = isNewsWatched,
        title = title
    )
}

val UI_DATE_TIME_FORMATTER_DATE_HRS_MIN: DateTimeFormatter =
    DateTimeFormatter.ofPattern("dd MMM yyyy HH:mm")

