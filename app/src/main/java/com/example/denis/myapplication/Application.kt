package com.example.denis.myapplication

import android.app.Application
import com.example.denis.myapplication.model.modules.module
import io.realm.Realm
import io.realm.RealmConfiguration
import org.koin.android.ext.android.startKoin

class Application : Application() {

    override fun onCreate() {
        super.onCreate();
        Realm.init(this);
        realmMigration();
        startKoin(this, module)
    }

    private fun realmMigration() {
        val config = RealmConfiguration.Builder()
            .name("realm.newsreader")
            .schemaVersion(0)
            .build();
        //here we can write migration
        Realm.setDefaultConfiguration(config);
    }
}