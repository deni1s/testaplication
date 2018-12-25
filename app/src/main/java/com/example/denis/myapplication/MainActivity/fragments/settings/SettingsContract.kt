package com.example.denis.myapplication.MainActivity.fragments.settings

import com.example.denis.myapplication.model.classes.Link
import com.example.denis.myapplication.model.utils.mvp.BasePresenter
import com.example.denis.myapplication.model.utils.mvp.BaseView


interface SettingsContract {
    interface View : BaseView<Presenter> {
        fun linkIsValid(link : Link)
        fun linkNotValid()
    }

    interface Presenter : BasePresenter<View> {
        fun checkUrl(urlToNews : String)
    }
}