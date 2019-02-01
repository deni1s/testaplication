package com.example.presentation.view.newslist

import com.example.model.Link
import com.example.presentation.utils.mvp.BasePresenter
import com.example.service.NewsRepositoryService
import com.example.service.Result
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class NewsListPresenter(
    val coroutineScope: CoroutineScope,
    val linksList: List<Link>,
    val newsRepository: NewsRepositoryService
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
        loadData(currentLinkPosition + 1)
    }

    private var currentLinkPosition = -1
    var isLoading: Boolean = false
    private var isAllLoaded: Boolean = false

    override fun reloadData() {
        isAllLoaded = false
        loadData(0)
    }

    private fun loadData(position: Int) {
        if (linksList.size > position) {
            job = coroutineScope.launch(Dispatchers.Default) {
                isLoading = true
                view!!.showProgressBar()
                val newsResponse = newsRepository.loadNewsList(linksList[position])
                isLoading = false
                if (newsResponse is Result.Success) {
                    currentLinkPosition = position
                    view!!.showNewsList(newsResponse.data)
                } else if (newsResponse is Result.Error) {
                    view?.hideProgressBar()
                    view?.showError(newsResponse.exception.localizedMessage)
                }
            }
        } else {
            isAllLoaded = true
            isLoading = false
            view!!.hideProgressBar()
            view!!.showNewsList(arrayListOf())
        }
    }

}