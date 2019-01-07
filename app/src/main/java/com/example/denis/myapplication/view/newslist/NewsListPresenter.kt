package com.example.denis.myapplication.view.newslist

import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.repositories.news.NewsRepository
import com.example.denis.myapplication.utils.coroutines.SchedulerProvider
import com.example.denis.myapplication.utils.coroutines.with
import com.example.denis.myapplication.utils.mvp.RxPresenter


class NewsListPresenter(val linksList : List<Link>, val newsRepository: NewsRepository, val schedulerProvider: SchedulerProvider)
    : RxPresenter<NewsListContract.View>(), NewsListContract.Presenter {

    override var view: NewsListContract.View? = null


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
            isLoading = true
            view!!.showProgressBar()
            launch {
                newsRepository.loadNewsList(linksList[position])
                    .with(schedulerProvider)
                    .subscribe(
                        { newsList ->
                            isLoading = false
                            currentLinkPosition = position
                            view!!.showNewsList(newsList)
                        },
                        { error -> view!!.showError(error.localizedMessage) })
            }
        } else {
            isAllLoaded = true
            isLoading = false
            view!!.hideProgressBar()
            view!!.showNewsList(arrayListOf())
        }
    }

}