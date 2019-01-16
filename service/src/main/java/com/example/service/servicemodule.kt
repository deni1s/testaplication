package com.example.service

import org.koin.dsl.module.module

val serviceModule = module {
    single { NewsRepositoryService(get(), get()) }
}