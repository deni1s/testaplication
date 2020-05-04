package com.example.presentation.view.newslist

import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView
import com.example.presentation.entity.NewsUM

interface NewsListContract {
    interface View : BaseView<Presenter> {
        fun showNewsList(newsList: List<NewsUM>)
        fun showProgressBar()
        fun hideProgressBar()
    }

    interface Presenter : BasePresenter<View> {
        fun loadNewsList()
        fun loanNextNewsPart()
        fun reloadData()
        fun openSettings()
        fun openNewsDetails(news: NewsUM)
    }
}
