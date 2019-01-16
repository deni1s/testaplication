package com.example.data.realm

import io.realm.Realm

open class BaseRealmRepository(val realm: Realm) {

   protected fun clearRealm() {
        realm.beginTransaction()
        realm.deleteAll()
        realm.commitTransaction()
    }
}