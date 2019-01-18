package com.example.data.realm

import com.example.model.News

interface NewsDatabaseInterface {
    fun isNewsWasWatched(news: News): Boolean
    fun addNewsItemRead(news: News)
}