package com.example.denis.myapplication.model.utils.mvp

import android.support.annotation.CallSuper
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class RxPresenter<V> : BasePresenter<V> {

    private val disposables = CompositeDisposable()

    fun launch(job: () -> Disposable) {
        disposables.add(job())
    }

    override fun subscribe(view: V) {
        this.view = view
    }

    @CallSuper
    override fun unSubscribe() {
        disposables.clear()
        view = null
    }
}