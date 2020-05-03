package com.example.presentation.view.newslist

import com.example.presentation.entity.NewsUM

interface NewsClickCallback {
    fun onNewsClicked(news : NewsUM)
}
