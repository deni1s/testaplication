package com.example.presentation.view.settings

import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView
import com.example.model.Link


interface SettingsContract {
    interface View : BaseView<Presenter> {
        fun linkIsValid(link : Link)
        fun linkNotValid()
    }

    interface Presenter : BasePresenter<View> {
        fun checkUrl(urlToNews : String)
    }
}