package com.example.presentation.view.newsdetails

import com.example.entity.news.NewsModel
import com.example.presentation.entity.NewsUM
import com.example.presentation.mappers.toDomainModel
import com.example.presentation.routing.NewsNavigator
import com.example.presentation.utils.mvp.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsDetailPresenter(
    val coroutineScope: CoroutineScope,
    val newsModel: NewsModel,
    val navigator: NewsNavigator
) :
    BasePresenter<NewsDetailContract.View>, NewsDetailContract.Presenter {
    override var view: NewsDetailContract.View? = null
    private lateinit var job: Job

    override fun setNewsDetails(newsDetail: NewsUM) {
        job = coroutineScope.launch(Dispatchers.Main) {
            newsModel.addNewsItemRead(newsDetail.toDomainModel())
            view!!.showNewsDetail(newsDetail)
        }
    }

    override fun popBackStack() {
        navigator.backIntent()
    }

    override fun unSubscribe() {
        view = null
        job.cancel()
    }
}
