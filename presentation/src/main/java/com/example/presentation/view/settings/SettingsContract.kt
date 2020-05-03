package com.example.presentation.view.settings

import com.example.presentation.utils.mvp.BasePresenter
import com.example.presentation.utils.mvp.BaseView
import com.example.entity.Link
import com.example.presentation.entity.LinkUM


interface SettingsContract {
    interface View : BaseView<Presenter> {
        fun linkSaved()
        fun linkNotValid()
        fun linkNotSupported()
        fun linkWasAddedBefore()
    }

    interface Presenter : BasePresenter<View> {
        fun saveUrl(urlToNews : String)
    }
}
