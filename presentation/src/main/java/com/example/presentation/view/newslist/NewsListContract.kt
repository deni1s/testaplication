package com.example.presentation.view.newslist

import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView
import com.example.model.News

interface NewsListContract {
    interface View : BaseView<Presenter> {
        fun showNewsList(newsList: List<News>)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : BasePresenter<View> {
        fun loadNewsList()
        fun reloadData()
    }
}