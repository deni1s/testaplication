package com.example.denis.myapplication.repositories.realm

import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.repositories.realm.BaseRealmRepository
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class LinksRealmRepository(realm: Realm) : BaseRealmRepository(realm) {

    private val LINK_KEY = "link"

    internal fun getLinkList(): RealmResults<Link> {
        val results = realm.where(Link::class.java).findAll()
        return results
    }

    internal fun addLinkToDataBase(link: Link) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(link)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }


    internal fun isLinkAlreadySaved(link: String?): Boolean {
        val user = realm.where(Link::class.java).equalTo(LINK_KEY, link).findFirst()
        return user != null
    }
}