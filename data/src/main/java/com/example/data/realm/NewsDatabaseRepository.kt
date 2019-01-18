package com.example.data.realm

import com.example.model.News
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class NewsDatabaseRepository(realm: Realm) :NewsDatabaseInterface, BaseDataBaseRepository(realm) {

    override fun isNewsWasWatched(news: News): Boolean {
        val user = realm.where(News::class.java).equalTo("title", news.title).findFirst()
        return user != null
    }

    override fun addNewsItemRead(news: News) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(news)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }
}