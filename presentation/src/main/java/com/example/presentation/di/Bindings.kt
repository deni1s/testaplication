package com.example.presentation.di

import android.app.Activity
import android.app.Application
import com.example.entity.domainModelModule
import com.example.repository.di.repositoryModule
import org.koin.android.ext.android.getKoin
import org.koin.android.ext.android.startKoin
import org.koin.dsl.module.Module
import org.koin.standalone.StandAloneContext

fun Activity.bindModules(viewModule: Module) {
    StandAloneContext.loadKoinModules(listOf(
        domainModelModule,
        repositoryModule,
        viewModule
    ))
}
