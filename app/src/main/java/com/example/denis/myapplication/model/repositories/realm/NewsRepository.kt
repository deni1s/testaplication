package com.example.denis.myapplication.model.repositories.realm

import com.example.denis.myapplication.model.classes.News
import io.realm.Realm
import io.realm.exceptions.RealmPrimaryKeyConstraintException

class NewsRepository(realm: Realm) : BaseRealmRepository(realm) {

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