package com.example.presentation.view.settings

import android.util.Patterns
import com.example.entity.Result
import com.example.entity.news.NewsModel
import com.example.entity.settings.SettingsModel
import com.example.presentation.routing.NewsNavigator
import com.example.presentation.utils.mvp.BasePresenter
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

class SettingsPresenter(
    val coroutineScope: CoroutineScope,
    val settingsModel: SettingsModel,
    val navigator: NewsNavigator
) :
    BasePresenter<SettingsContract.View>,
    SettingsContract.Presenter {

    private lateinit var job: Job

    override fun unSubscribe() {
        view = null
        if (::job.isInitialized) {
            job.cancel()
        }
    }

    override var view: SettingsContract.View? = null

    override fun saveUrl(urlToNews: String) {
        if (view != null) {
            if (urlToNews.isLinkValid()) {
                job = coroutineScope.launch(Dispatchers.Main) {
                    val result = settingsModel.saveLink(linkUrl = urlToNews)
                    if (result is Result.Success) {
                        view!!.linkSaved()
                        navigator.backIntent()
                    } else if (result is Result.Error) {
                        view!!.linkNotSupported()
                    }
                }

            } else {
                view!!.linkNotValid()
            }
        }
    }

    override fun popBackStack() {
        navigator.backIntent()
    }
}

private fun String.isLinkValid(): Boolean {
    return isNotEmpty() && Patterns.WEB_URL.matcher(this).matches();
}
