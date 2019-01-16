package com.example.data.realm

import com.example.data.realm.BaseRealmRepository
import com.example.model.News
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class NewsRealmRepository(realm: Realm) : BaseRealmRepository(realm) {

    fun isNewsWasWatched(news: News): Boolean {
        val user = realm.where(News::class.java).equalTo("title", news.title).findFirst()
        return user != null
    }

    fun addNewsItemRead(news: News) {
        realm.beginTransaction()
        try {
            realm.insertOrUpdate(news)
            realm.commitTransaction()
        } catch (e: RealmPrimaryKeyConstraintException) {
            realm.cancelTransaction()
        }
    }
}