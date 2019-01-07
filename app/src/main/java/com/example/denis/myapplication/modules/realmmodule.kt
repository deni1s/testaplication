package com.example.denis.myapplication.modules

import com.example.denis.myapplication.repositories.realm.LinksRealmRepository
import com.example.denis.myapplication.repositories.realm.NewsRealmRepository
import io.realm.Realm
import org.koin.dsl.module.module

val realmModule = module {

    single { getRealmInstance() }
    single{ LinksRealmRepository(get()) }
    single { NewsRealmRepository(get()) }
}

fun getRealmInstance(): Realm {
    return Realm.getDefaultInstance()
}