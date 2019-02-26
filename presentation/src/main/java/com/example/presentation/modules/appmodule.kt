package com.example.presentation.modules


import com.example.data.module.apiModule
import com.example.data.module.realmModule
import com.example.presentation.view.settings.SettingsContract
import com.example.presentation.view.settings.SettingsPresenter
import com.example.model.Link
import com.example.presentation.view.newsdetails.NewsDetailContract
import com.example.presentation.view.newsdetails.NewsDetailPresenter
import com.example.presentation.view.newslist.NewsListContract
import com.example.presentation.view.newslist.NewsListPresenter
import com.example.service.serviceModule
import kotlinx.coroutines.CoroutineScope

import org.koin.dsl.module.module

val viewModule = module {
    factory { (linkList: List<Link>, coroutineScope : CoroutineScope) -> NewsListPresenter(coroutineScope, linkList, get()) as NewsListContract.Presenter }
    factory { NewsDetailPresenter(get()) as NewsDetailContract.Presenter }
    factory { (coroutineScope : CoroutineScope) ->  SettingsPresenter(coroutineScope, get()) as SettingsContract.Presenter }
}

val module = listOf(
    realmModule,
    serviceModule,
    apiModule,
   viewModule
)