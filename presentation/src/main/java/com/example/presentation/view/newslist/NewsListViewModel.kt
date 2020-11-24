package com.example.presentation.view.newslist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.news.NewsModel
import com.example.entity.Result
import com.example.presentation.entity.NewsUM
import com.example.presentation.mappers.toUiModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsListViewModel @Inject constructor(
    private val newsModel: NewsModel,
) : ViewModel() {

    private lateinit var job: Job
    private var isLoading: Boolean = false
    private var isAllLoaded: Boolean = false

    private var showProgressBarListener: ((Boolean) -> Unit)? = null
    private var showErrorListener: ((String) -> Unit)? = null
    private var showNewsListListener: ((List<NewsUM>) -> Unit)? = null

    fun loadNewsList() {
        if (isLoading || isAllLoaded) {
            return
        }
        loadData()
    }

    fun loanNextNewsPart() {
        isLoading = true
        showProgressBarListener?.invoke(true)
        job = viewModelScope.launch(Dispatchers.Main) {
            val newsResponse = newsModel.loadNextNewsListPart()
            isLoading = false
            if (newsResponse is Result.Success) {
                showNewsListListener?.invoke(newsResponse.data.toUiModel())
            } else if (newsResponse is Result.Error) {
                showProgressBarListener?.invoke(false)
                showErrorListener?.invoke(newsResponse.exception.localizedMessage)
            }
        }
    }

    fun reloadData() {
        isAllLoaded = false
        loadData()
    }

    private fun loadData() {
        isLoading = true
        showProgressBarListener?.invoke(true)
        job = viewModelScope.launch(Dispatchers.Main) {
            newsModel.loadNewsList().collect { newsResponse ->
                isLoading = false
                if (newsResponse is Result.Success) {
                    showNewsListListener?.invoke(newsResponse.data.toUiModel())
                } else if (newsResponse is Result.Error) {
                    showProgressBarListener?.invoke(false)
                    showErrorListener?.invoke(newsResponse.exception.localizedMessage)
                }
            }
        }
    }

    override fun onCleared() {
        job.cancel()
    }

    fun setShowProgressBarListener(listener: (Boolean) -> Unit) {
        showProgressBarListener = listener
    }

    fun setShowErrorListener(listener: (String) -> Unit) {
        showErrorListener = listener
    }

    fun setShowNewsListListener(listener: (List<NewsUM>) -> Unit) {
        showNewsListListener = listener
    }
}
