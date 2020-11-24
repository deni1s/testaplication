package com.example.presentation.view.newsdetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.news.NewsModel
import com.example.presentation.entity.NewsUM
import com.example.presentation.mappers.toDomainModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class NewsDetailViewModel @Inject constructor(
    private val newsModel: NewsModel,
) : ViewModel() {

    private lateinit var job: Job
    private var showNewsDetailsClickListener: ((NewsUM) -> Unit)? = null

    fun setNewsDetails(newsDetail: NewsUM) {
        job = viewModelScope.launch(Dispatchers.Main) {
            newsModel.addNewsItemRead(newsDetail.toDomainModel())
            showNewsDetailsClickListener!!.invoke(newsDetail)
        }
    }

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }

    fun setShowNewsDetailsListener(listener: (NewsUM) -> Unit) {
        showNewsDetailsClickListener = listener
    }
}
