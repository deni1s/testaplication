package com.example.app.menu.di

import com.example.data.module.apiModule
import com.example.storage.di.storageModule

val module = listOf(
    apiModule,
    storageModule
)
