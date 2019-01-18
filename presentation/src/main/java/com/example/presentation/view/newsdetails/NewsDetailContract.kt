package com.example.presentation.view.newsdetails

import com.example.model.News
import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView

interface NewsDetailContract {
    interface View : BaseView<Presenter> {
        fun showNewsDetail(newsDetail: News)
    }

    interface Presenter : BasePresenter<View> {
        fun setNewsDetails(newsDetail: News)
    }
}