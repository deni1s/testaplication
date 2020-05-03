package com.example.presentation.di

import com.example.entity.Link
import com.example.presentation.view.newsdetails.NewsDetailContract
import com.example.presentation.view.newsdetails.NewsDetailPresenter
import com.example.presentation.view.newslist.NewsListContract
import com.example.presentation.view.newslist.NewsListPresenter
import com.example.presentation.view.settings.SettingsContract
import com.example.presentation.view.settings.SettingsPresenter
import kotlinx.coroutines.CoroutineScope
import org.koin.dsl.module.module

val viewModule = module {
    factory { (coroutineScope: CoroutineScope) ->
        NewsListPresenter(coroutineScope, get()) as NewsListContract.Presenter
    }
    factory { (coroutineScope: CoroutineScope) ->
        NewsDetailPresenter(
            coroutineScope,
            get()
        ) as NewsDetailContract.Presenter
    }
    factory { (coroutineScope: CoroutineScope) ->
        SettingsPresenter(
            coroutineScope,
            get()
        ) as SettingsContract.Presenter
    }
}
