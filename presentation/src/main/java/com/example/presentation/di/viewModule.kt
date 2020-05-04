package com.example.presentation.di

import androidx.navigation.NavController
import com.example.presentation.routing.NewsNavigator
import com.example.presentation.routing.NewsNavigatorImpl
import com.example.presentation.view.newsdetails.NewsDetailContract
import com.example.presentation.view.newsdetails.NewsDetailPresenter
import com.example.presentation.view.newslist.NewsListContract
import com.example.presentation.view.newslist.NewsListPresenter
import com.example.presentation.view.settings.SettingsContract
import com.example.presentation.view.settings.SettingsPresenter
import kotlinx.coroutines.GlobalScope
import org.koin.dsl.module.Module
import org.koin.dsl.module.module

fun createNewsViewModule(navController: NavController): Module {
    return module {
        single { NewsNavigatorImpl(navController) as NewsNavigator }
        factory { NewsListPresenter(GlobalScope, get(), get()) as NewsListContract.Presenter }
        factory { NewsDetailPresenter(GlobalScope, get(), get()) as NewsDetailContract.Presenter }
        factory { SettingsPresenter(GlobalScope, get(), get()) as SettingsContract.Presenter }
    }
}
