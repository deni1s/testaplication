package com.example.app.menu.di

import com.example.data.module.apiModule
import com.example.entity.domainModelModule
import com.example.presentation.di.viewModule
import com.example.repository.di.repositoryModule
import com.example.storage.di.storageModule

val module = listOf(
    domainModelModule,
    apiModule,
    viewModule,
    repositoryModule,
    storageModule
)
