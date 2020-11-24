package com.example.presentation.view.settings

import android.app.Application
import android.util.Patterns
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.entity.Result
import com.example.entity.settings.SettingsModel
import com.example.presentation.R
import com.example.presentation.entity.LinkUM
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import javax.inject.Inject

class SettingsViewModel @Inject constructor(
    private val settingsModel: SettingsModel,
    private val application: Application,
) : ViewModel() {

    private lateinit var job: Job
    private var showLinkListListener: ((List<LinkUM>) -> Unit)? = null
    private var showToastListener: ((String) -> Unit)? = null
    private var popBackStackListener: (() -> Unit)? = null

    override fun onCleared() {
        if (::job.isInitialized) {
            job.cancel()
        }
    }

    fun fetchLinkList() {
        job = viewModelScope.launch(Dispatchers.Main) {
            val result = settingsModel.getLinkList()
            if (result is Result.Success) {
                showLinkListListener?.invoke(result.data)
            }
        }
    }


    fun saveUrl(urlToNews: String) {
        if (urlToNews.isLinkValid()) {
            job = viewModelScope.launch(Dispatchers.Main) {
                val result = settingsModel.saveLink(linkUrl = urlToNews)
                if (result is Result.Success) {
                    showToastListener?.invoke(application.getString(R.string.alert_success_link_adding))
                    popBackStackListener?.invoke()
                } else if (result is Result.Error) {
                    showToastListener?.invoke(application.getString(R.string.link_not_supported))
                }
            }

        } else {
            showToastListener?.invoke(application.getString(R.string.alert_wrong_url))
        }
    }

    fun saveLink(link: LinkUM) {
        job = viewModelScope.launch(Dispatchers.Main) {
            val result = settingsModel.saveLink(link)
            if (result is Result.Success) {
                showToastListener?.invoke(application.getString(R.string.alert_success_link_adding))
                popBackStackListener?.invoke()
            } else if (result is Result.Error) {
                showToastListener?.invoke(application.getString(R.string.link_not_supported))
            }
        }
    }

    fun setShowToastListener(listener: (String) -> Unit) {
        showToastListener = listener
    }

     fun setShowLinkListListener(listener: (List<LinkUM>) -> Unit) {
        showLinkListListener = listener
    }

    fun setPopBackStackListener(listener: () -> Unit) {
        popBackStackListener = listener
    }

}

private fun String.isLinkValid(): Boolean {
    return isNotEmpty() && Patterns.WEB_URL.matcher(this).matches();
}
