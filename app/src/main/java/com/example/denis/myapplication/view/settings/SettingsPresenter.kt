package com.example.denis.myapplication.view.settings

import android.util.Patterns
import com.example.denis.myapplication.repositories.news.NewsRepository
import com.example.denis.myapplication.utils.coroutines.SchedulerProvider
import com.example.denis.myapplication.utils.coroutines.with
import com.example.denis.myapplication.utils.mvp.RxPresenter

class SettingsPresenter(val newsRepository: NewsRepository, val schedulerProvider: SchedulerProvider)
    : RxPresenter<SettingsContract.View>(), SettingsContract.Presenter {

    override var view: SettingsContract.View? = null

    override fun checkUrl(urlToNews: String) {
        if (view != null) {
            if (isLinkValid(urlToNews)) {
                launch {
                    newsRepository.getCheckUrlObservable(urlToNews)
                        .with(schedulerProvider)
                        .subscribe(
                            { link ->
                                if (!link.isEmpty()) {
                                    view!!.linkIsValid(link)
                                } else {
                                    view!!.linkNotValid()
                                }
                            },
                            { t: Throwable ->
                                view!!.showError(t.localizedMessage)
                            })
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