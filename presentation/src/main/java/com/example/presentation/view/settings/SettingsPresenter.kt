package com.example.presentation.view.settings

import android.util.Patterns
import com.example.data.news.NewsRepository
import com.example.presentation.utils.CoroutineContextProvider
import com.example.presentation.utils.mvp.BasePresenter
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsPresenter(val contextProvider: CoroutineContextProvider, val newsRepository: NewsRepository) :
    BasePresenter<SettingsContract.View>,
    SettingsContract.Presenter {

    private lateinit var job: Job

    override fun unSubscribe() {
        view = null
        if(::job.isInitialized) {
            job.cancel()
        }
    }

    override var view: SettingsContract.View? = null

    override fun checkUrl(urlToNews: String) {
        if (view != null) {
            if (isLinkValid(urlToNews)) {
                job = contextProvider.uiScope.launch {
                    try {
                        val link = newsRepository.getCheckUrlObservable(urlToNews)
                        if (!link.isEmpty()) {
                            view!!.linkIsValid(link)
                        } else {
                            view!!.linkNotValid()
                        }
                    } catch (e: Exception) {
                        view!!.linkNotValid()
                    }
                }

            } else {
                view!!.linkNotValid()
            }
        }
    }

    fun isLinkValid(link: String): Boolean {
        return !link.isEmpty() && Patterns.WEB_URL.matcher(link).matches();
    }
}