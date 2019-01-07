package com.example.denis.myapplication.view

import com.example.denis.myapplication.BuildConfig
import com.example.denis.myapplication.data.Link
import com.example.denis.myapplication.data.News
import com.example.denis.myapplication.repositories.news.NewsApiInterface
import com.example.denis.myapplication.repositories.news.NewsRepository
import com.example.denis.myapplication.utils.MockitoHelper
import com.example.denis.myapplication.utils.TestSchedulerProvider
import com.example.denis.myapplication.view.newslist.NewsListContract
import com.example.denis.myapplication.view.newslist.NewsListPresenter
import io.reactivex.Single
import okhttp3.OkHttpClient
import org.junit.Before
import org.junit.Test
import org.mockito.BDDMockito
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

class NewsListPresenterTest {

    lateinit var presenter: NewsListContract.Presenter
    @Mock
    lateinit var view: NewsListContract.View

    @Mock
    lateinit var newsRepository: NewsRepository


    @Before
    fun before() {
        MockitoAnnotations.initMocks(this)

        presenter = NewsListPresenter(listOf(getJsonLink(), getRssLink(), getInvalidLink()), newsRepository, TestSchedulerProvider())
        presenter.subscribe(view)
    }

    fun getNewHttpClient(): OkHttpClient {
        val client = OkHttpClient.Builder()
            .followRedirects(true)
            .followSslRedirects(true)
            .retryOnConnectionFailure(true)
            .cache(null)
            .connectTimeout(30, TimeUnit.SECONDS)
            .writeTimeout(30, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)

        return client.build()
    }

    fun createRssApi(): NewsApiInterface {

        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getNewHttpClient())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NewsApiInterface::class.java)
    }

    fun createJsonApi(): NewsApiInterface {

        val retrofit = retrofit2.Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .client(getNewHttpClient())
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

        return retrofit.create(NewsApiInterface::class.java)
    }

    @Test
    fun testDisplayList() {
        BDDMockito.given(newsRepository.loadNewsList(getJsonLink())).willReturn(Single.just(MockedData.mockList))

        presenter.loadNewsList()

        val itemList = MockedData.mockList.takeLast(MockedData.mockList.size - 1).map { News.from(it) }
        Mockito.verify(view, Mockito.never()).showError(MockitoHelper.any())
        Mockito.verify(view).showNewsList(itemList)
    }

    @Test
    fun testDisplayListFailed() {
        val error = Throwable("Got an error")
        BDDMockito.given(newsRepository.loadNewsList(getJsonLink())).willReturn(Single.error(error))

        presenter.loadNewsList()

//        Mockito.verify(view, Mockito.never()).showNewsList(MockitoHelper.any())
        Mockito.verify(view).showError(error.localizedMessage)
    }

    private fun getJsonLink(): Link {
        val link = Link()
        link.isJson = true
        link.link =
                "https://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2778ba9d0314458596009ce5650f906f"
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