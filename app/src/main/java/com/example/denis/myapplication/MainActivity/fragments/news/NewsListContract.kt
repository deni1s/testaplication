package com.example.denis.myapplication.MainActivity.fragments.news

import com.example.denis.myapplication.model.classes.News
import com.example.denis.myapplication.model.utils.mvp.BasePresenter
import com.example.denis.myapplication.model.utils.mvp.BaseView

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