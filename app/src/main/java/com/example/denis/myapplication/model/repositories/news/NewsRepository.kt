package com.example.denis.myapplication.model.repositories.news

import com.example.denis.myapplication.model.classes.Link
import com.example.denis.myapplication.model.classes.News
import com.example.denis.myapplication.model.classes.responses.NewsResponse
import io.reactivex.Single
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.functions.BiFunction
import io.reactivex.functions.Function
import io.reactivex.schedulers.Schedulers


class NewsRepository(val jsonNewsApiInterface : NewsApiInterface, val rssNewsApiInterface : NewsApiInterface) {


    fun getNewsInterface(link: Link): Single<NewsResponse> {
        if (link.isJson) {
            return getJsonNewsInterface(link.link)
        } else {
            return getRssNewsInterface(link.link)
        }
    }

    fun getJsonNewsInterface(url: String): Single<NewsResponse> {
        return jsonNewsApiInterface.loadJsonNews(url)
    }

    fun loadNewsList(link: Link): Single<List<News>> {
        return getNewsInterface(link)
            .map { t: NewsResponse -> t.getNewsList() }
            .retry(1)
    }

    fun getRssNewsInterface(url: String): Single<NewsResponse> {
        return rssNewsApiInterface.loadRssNews(url)
    }


    fun getCheckUrlObservable(linkString: String): Single<Link> {
        val responseOneObservable = getJsonNewsInterface(linkString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn(object : Function<Throwable, NewsResponse> {
                override fun apply(t: Throwable): NewsResponse {
                    return NewsResponse()
                }
            })

        val responseTwoObservable = getRssNewsInterface(linkString)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .onErrorReturn(object : Function<Throwable, NewsResponse> {
                override fun apply(t: Throwable): NewsResponse {
                    return NewsResponse()
                }
            })

        return Single.zip(responseOneObservable,
            responseTwoObservable,
            BiFunction<NewsResponse, NewsResponse, Link>
            { responseJson, responseRss ->
                val link = Link()
                try {
                    if (responseJson.getNewsList().size != 0) {
                        link.link = linkString
                        link.isJson = true

                    } else if (responseRss.getNewsList().size != 0) {
                        link.link = linkString
                        link.isJson = false
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                }
                return@BiFunction link!!
            })
    }

}