package com.example.denis.myapplication.model.modules

import com.example.denis.myapplication.model.repositories.realm.LinksRepository
import com.example.denis.myapplication.model.repositories.realm.NewsRepository
import io.realm.Realm
import org.koin.dsl.module.module

val realmModule = module {

    single { getRealmInstance() }
    single{ LinksRepository(get()) }
    single { NewsRepository(get()) }
}

fun getRealmInstance(): Realm {
    return Realm.getDefaultInstance()
}