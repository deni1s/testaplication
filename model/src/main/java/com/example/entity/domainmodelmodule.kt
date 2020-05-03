package com.example.entity

import com.example.entity.news.NewsModel
import com.example.entity.news.NewsModelImpl
import com.example.entity.settings.SettingsModel
import com.example.entity.settings.SettingsModelImpl
import org.koin.dsl.module.module

val domainModelModule = module {
    single { NewsModelImpl(get(), get()) as NewsModel }
    single { SettingsModelImpl(get(), get()) as SettingsModel }
}
