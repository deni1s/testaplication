package com.example.denis.myapplication.utils.mvp

import com.example.denis.myapplication.utils.mvp.BasePresenter

interface BaseView<out T : BasePresenter<*>> {

    fun showError(error: String)

    val presenter: T
}