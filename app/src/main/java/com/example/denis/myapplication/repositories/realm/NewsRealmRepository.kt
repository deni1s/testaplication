package com.example.denis.myapplication.repositories.realm

import com.example.denis.myapplication.data.News
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class NewsRealmRepository(realm: Realm) : BaseRealmRepository(realm) {

    internal fun isNewsWasWatched(news: News): Boolean {
        val user = realm.where(News::class.java).equalTo("title", news.title).findFirst()
        return user != null
    }

    internal fun addNewsItemRead(news: News) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(news)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }
}