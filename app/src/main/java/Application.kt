package com.example.app

import android.app.Application
import com.example.app.menu.di.module
import org.koin.android.ext.android.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin(this, module)
    }
}
