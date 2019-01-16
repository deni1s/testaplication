package com.example.data.module

import com.example.data.realm.LinksRealmRepository
import com.example.data.realm.NewsRealmRepository
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