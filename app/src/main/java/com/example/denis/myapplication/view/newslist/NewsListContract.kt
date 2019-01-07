package com.example.denis.myapplication.view.newslist

import com.example.denis.myapplication.data.News
import com.example.denis.myapplication.utils.mvp.BasePresenter
import com.example.denis.myapplication.utils.mvp.BaseView

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