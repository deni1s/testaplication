package com.example.denis.myapplication.model.modules

import com.example.denis.myapplication.MainActivity.fragments.news.NewsListContract
import com.example.denis.myapplication.MainActivity.fragments.news.NewsListPresenter
import com.example.denis.myapplication.MainActivity.fragments.settings.SettingsContract
import com.example.denis.myapplication.MainActivity.fragments.settings.SettingsPresenter
import com.example.denis.myapplication.model.classes.Link
import com.example.denis.myapplication.model.utils.coroutines.ApplicationSchedulerProvider
import com.example.denis.myapplication.model.utils.coroutines.SchedulerProvider
import org.koin.dsl.module.applicationContext
import org.koin.dsl.module.module

val appModule = module {
    factory { (linkList: List<Link>) -> NewsListPresenter(linkList, get(), get()) as NewsListContract.Presenter }

    factory {SettingsPresenter(get(), get()) as SettingsContract.Presenter }
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