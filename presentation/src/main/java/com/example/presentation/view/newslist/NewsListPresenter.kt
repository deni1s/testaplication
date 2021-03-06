package com.example.presentation.view.newslist

import com.example.entity.news.NewsModel
import com.example.presentation.utils.mvp.BasePresenter
import com.example.entity.Result
import com.example.presentation.entity.NewsUM
import com.example.presentation.mappers.toUiModel
import com.example.presentation.routing.NewsNavigator
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class NewsListPresenter(
    val coroutineScope: CoroutineScope,
    val newsModel: NewsModel,
    val navigator: NewsNavigator
) : BasePresenter<NewsListContract.View>, NewsListContract.Presenter {

    override fun unSubscribe() {
        view = null
        job.cancel()
    }

    override var view: NewsListContract.View? = null
    private lateinit var job: Job

    override fun loadNewsList() {
        if (isLoading || isAllLoaded) {
            return
        }
        loadData()
    }

    override fun loanNextNewsPart() {
        isLoading = true
        view!!.showProgressBar()
        job = coroutineScope.launch(Dispatchers.Main) {
            val newsResponse = newsModel.loadNextNewsListPart()
            isLoading = false
            if (newsResponse is Result.Success) {
                view!!.showNewsList(newsResponse.data.toUiModel())
            } else if (newsResponse is Result.Error) {
                view?.hideProgressBar()
                view?.showError(newsResponse.exception.localizedMessage)
            }
        }
    }

    var isLoading: Boolean = false
    private var isAllLoaded: Boolean = false

    override fun reloadData() {
        isAllLoaded = false
        loadData()
    }

    override fun openSettings() {
        navigator.settingsRequired()
    }

    override fun openNewsDetails(news: NewsUM) {
        navigator.newsDetailsRequired(news)
    }

    private fun loadData() {
        isLoading = true
        view!!.showProgressBar()
        job = coroutineScope.launch(Dispatchers.Main) {
            newsModel.loadNewsList().collect { newsResponse ->
                isLoading = false
                if (newsResponse is Result.Success) {
                    view!!.showNewsList(newsResponse.data.toUiModel())
                } else if (newsResponse is Result.Error) {
                    view?.hideProgressBar()
                    view?.showError(newsResponse.exception.localizedMessage)
                }
            }
        }
    }
}
