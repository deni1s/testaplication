package com.example.presentation.view.newsdetails

import com.example.data.realm.NewsDatabaseInterface
import com.example.model.News
import com.example.presentation.utils.mvp.BasePresenter

class NewsDetailPresenter(val newsRepository: NewsDatabaseInterface) :
    BasePresenter<NewsDetailContract.View>, NewsDetailContract.Presenter {
    override var view: NewsDetailContract.View? = null


    override fun setNewsDetails(newsDetail: News) {
        newsRepository.addNewsItemRead(newsDetail)
        view!!.showNewsDetail(newsDetail)
    }

    override fun unSubscribe() {
        view = null
    }
}