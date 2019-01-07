package com.example.denis.myapplication.view.newslist

import com.example.denis.myapplication.data.News


interface NewsClickCallback {
    fun onNewsClicked(news : News)
}