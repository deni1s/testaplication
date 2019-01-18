package com.example.data.realm

import com.example.model.Link
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class LinksDatabaseRepository(realm: Realm) : BaseDataBaseRepository(realm), LinksDatabaseInterface {

    private val LINK_KEY = "link"

    override fun getLinkList(): List<Link> {
        val results = realm.where(Link::class.java).findAll()
        return results
    }

    override fun addLinkToDataBase(link: Link) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(link)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }


    override fun isLinkAlreadySaved(link: String?): Boolean {
        val user = realm.where(Link::class.java).equalTo(LINK_KEY, link).findFirst()
        return user != null
    }
}