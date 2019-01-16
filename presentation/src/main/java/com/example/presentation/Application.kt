package com.example.presentation

import android.support.multidex.MultiDexApplication
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin
import com.example.presentation.modules.module

class Application : MultiDexApplication() {

    override fun onCreate() {
        super.onCreate()
        Realm.init(this)
        realmMigration()
        startKoin(this, module)
    }

    private fun realmMigration() {
        val config = RealmConfiguration.Builder()
            .name("realm.newsreader")
            .schemaVersion(0)
            .build()
        //here we can write migration
        Realm.setDefaultConfiguration(config)
    }
}