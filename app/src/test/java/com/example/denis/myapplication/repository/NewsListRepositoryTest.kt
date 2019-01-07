package com.example.denis.myapplication.repository

import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.modules.testNewsApp
import com.example.denis.myapplication.repositories.news.NewsRepository
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.koin.standalone.StandAloneContext
import org.koin.standalone.inject
import org.koin.test.KoinTest

class NewsListRepositoryTest : KoinTest {
    val repository by inject<NewsRepository>()

    @Before
    fun before() {
        StandAloneContext.startKoin(testNewsApp)
    }

    @After
    fun after() {
        StandAloneContext.stopKoin()
    }

    @Test
    fun testRssLink() {
        val link = getRssLink()
        repository.loadNewsList(link).blockingGet()
        val test = repository.loadNewsList(link).test()
        test.awaitTerminalEvent()
        test.assertComplete()
    }

    @Test
    fun testJsonLink() {
        val link = getJsonLink()
        repository.loadNewsList(link).blockingGet()
        val test = repository.loadNewsList(link).test()
        test.awaitTerminalEvent()
        test.assertComplete()
    }

    @Test
    fun testInvalidLink() {
        val link = getInvalidLink()
        repository.loadNewsList(link).blockingGet()
        val test = repository.loadNewsList(link).test()
        test.awaitTerminalEvent()
        test.assertComplete()
    }

    private fun getJsonLink(): Link {
        val link = Link()
        link.isJson = true
        link.link = "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2778ba9d0314458596009ce5650f906f"
        return link
    }

    private fun getInvalidLink(): Link {
        val link = Link()
        link.isJson = true
        link.link = "https://vk.com/"
        return link
    }

    private fun getRssLink(): Link {
        val link = Link()
        link.isJson = false
        link.link = "https://habr.com/rss/all/all/"
        return link
    }
}