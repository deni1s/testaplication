package com.example.presentation.modules


import com.example.data.module.apiModule
import com.example.data.module.realmModule
import com.example.presentation.utils.CoroutineContextProvider
import com.example.presentation.view.settings.SettingsContract
import com.example.presentation.view.settings.SettingsPresenter
import com.example.model.Link
import com.example.presentation.view.newslist.NewsListContract
import com.example.presentation.view.newslist.NewsListPresenter
import com.example.service.NewsRepositoryService
import com.example.service.serviceModule

import org.koin.dsl.module.module

val viewModule = module {
    single { CoroutineContextProvider() }
    factory { (linkList: List<Link>) -> NewsListPresenter(get(), linkList, get()) as NewsListContract.Presenter }
    factory { SettingsPresenter(get(), get()) as SettingsContract.Presenter }
}

val module = listOf(
    realmModule,
    serviceModule,
    apiModule,
   viewModule
)