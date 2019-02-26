package com.example.data.module

import com.example.data.realm.LinksDatabaseInterface
import com.example.data.realm.LinksDatabaseRepository
import com.example.data.realm.NewsDatabaseInterface
import com.example.data.realm.NewsDatabaseRepository
import io.realm.Realm
import org.koin.dsl.module.module

val realmModule = module {

    single { getRealmInstance() }
    single { LinksDatabaseRepository(get()) as LinksDatabaseInterface }
    single { NewsDatabaseRepository(get()) as NewsDatabaseInterface }
}

fun getRealmInstance(): Realm {
    return Realm.getDefaultInstance()
}