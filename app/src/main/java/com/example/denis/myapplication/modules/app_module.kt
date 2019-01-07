package com.example.denis.myapplication.modules

import com.example.denis.myapplication.view.newslist.NewsListContract
import com.example.denis.myapplication.view.newslist.NewsListPresenter
import com.example.denis.myapplication.view.settings.SettingsContract
import com.example.denis.myapplication.view.settings.SettingsPresenter
import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.utils.coroutines.ApplicationSchedulerProvider
import com.example.denis.myapplication.utils.coroutines.SchedulerProvider
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

val appModule = module {
    factory { (linkList: List<Link>) -> NewsListPresenter(linkList, get(), get()) as NewsListContract.Presenter }

    factory { SettingsPresenter(get(), get()) as SettingsContract.Presenter }
}

val rxModule = applicationContext {
    // provided components
    bean { ApplicationSchedulerProvider() as SchedulerProvider }
}

val module = listOf(
    appModule,
    realmModule,
    rxModule,
    remoteDataSourceModule
)