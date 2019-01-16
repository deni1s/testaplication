package com.example.presentation.view.newslist

import com.example.model.News


interface NewsClickCallback {
    fun onNewsClicked(news : News)
}