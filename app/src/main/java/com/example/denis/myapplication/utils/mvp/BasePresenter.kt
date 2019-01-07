package com.example.denis.myapplication.utils.mvp

interface BasePresenter<V> {

    fun subscribe(view: V)

    fun unSubscribe()

    var view : V?

}