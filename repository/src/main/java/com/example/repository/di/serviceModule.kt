package com.example.repository.di

import com.example.repository.links.LinkRepository
import com.example.repository.links.LinkRepositoryImpl
import com.example.repository.news.NewsRepository
import com.example.repository.news.NewsRepositoryImpl
import org.koin.dsl.module.module

val repositoryModule = module {
    single { NewsRepositoryImpl(get(), get(), get()) as NewsRepository }
    single { LinkRepositoryImpl(get()) as LinkRepository }
}
