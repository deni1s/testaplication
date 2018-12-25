package com.example.denis.myapplication.model.repositories.realm

import com.example.denis.myapplication.model.classes.Link
import io.realm.Realm
import io.realm.RealmResults
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class LinksRepository(realm: Realm) : BaseRealmRepository(realm) {

    private val LINK_KEY = "link"

    fun getLinkList(): RealmResults<Link> {
        val results = realm.where(Link::class.java).findAll()
        return results
    }

    fun addLinkToDataBase(link: Link) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(link)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }


    fun isLinkAlreadySaved(link: String?): Boolean {
        val user = realm.where(Link::class.java).equalTo(LINK_KEY, link).findFirst()
        return user != null
    }
}