package com.example.denis.myapplication.view.settings

import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.utils.mvp.BasePresenter
import com.example.denis.myapplication.utils.mvp.BaseView


interface SettingsContract {
    interface View : BaseView<Presenter> {
        fun linkIsValid(link : Link)
        fun linkNotValid()
    }

    interface Presenter : BasePresenter<View> {
        fun checkUrl(urlToNews : String)
    }
}