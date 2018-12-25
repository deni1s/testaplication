package com.example.denis.myapplication.model.utils.mvp

interface BaseView<out T : BasePresenter<*>> {

    fun showError(error: String)

    val presenter: T
}