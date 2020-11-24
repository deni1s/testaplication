package com.example.repository.di

import com.example.data.di.NetworkModule
import com.example.repository.links.LinkRepository
import com.example.repository.links.LinkRepositoryImpl
import com.example.repository.news.NewsRepository
import com.example.repository.news.NewsRepositoryImpl
import com.example.storage.di.StorageModule
import dagger.Binds
import dagger.Module

@Module(includes = [StorageModule::class, NetworkModule::class])
abstract class RepositoryModule {
    @Binds
    abstract fun provideNewsRepository(repository: NewsRepositoryImpl): NewsRepository

    @Binds
    abstract fun provideLinkRepository(repositoryImpl: LinkRepositoryImpl): LinkRepository
}
