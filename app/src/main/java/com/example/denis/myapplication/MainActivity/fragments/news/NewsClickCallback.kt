package com.example.denis.myapplication.MainActivity.fragments.news

import com.example.denis.myapplication.model.classes.News

interface NewsClickCallback {
    fun onNewsClicked(news : News)
}