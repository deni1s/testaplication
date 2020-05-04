package com.example.presentation.view.newsdetails

import com.example.presentation.entity.NewsUM
import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView

interface NewsDetailContract {
    interface View : BaseView<Presenter> {
        fun showNewsDetail(newsDetail: NewsUM)
    }

    interface Presenter : BasePresenter<View> {
        fun setNewsDetails(newsDetail: NewsUM)
        fun popBackStack()
    }
}
