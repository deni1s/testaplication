package com.example.entity.di

import com.example.entity.news.NewsModel
import com.example.entity.news.NewsModelImpl
import com.example.entity.settings.SettingsModel
import com.example.entity.settings.SettingsModelImpl
import com.example.repository.di.RepositoryModule
import dagger.Binds
import dagger.Module

@Module(includes = [RepositoryModule::class])
abstract class DomainModule {
    @Binds
    abstract fun provideNewsModel(model: NewsModelImpl): NewsModel

    @Binds
    abstract fun provideSettingsModel(modelImpl: SettingsModelImpl): SettingsModel
}
