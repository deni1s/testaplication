package com.example.presentation.routing

import com.example.presentation.entity.NewsUM

interface NewsNavigator {
    fun newsDetailsRequired(news: NewsUM)
    fun settingsRequired()
    fun backIntent()
}

const val NEWS_PARAM = "news"
