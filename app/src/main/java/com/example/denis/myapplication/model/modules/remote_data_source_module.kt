package com.example.denis.myapplication.model.modules

import com.example.denis.myapplication.BuildConfig
import com.example.denis.myapplication.model.repositories.news.NewsRepository
import com.example.denis.myapplication.model.repositories.news.NewsApiInterface
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    // provided web components
    single { getNewHttpClient() }
    single {
        NewsRepository(
            createJsonApi(get()),
            createRssApi(get())
        )
    }
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

fun createJsonApi(okHttpClient: OkHttpClient): NewsApiInterface {

    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(GsonConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(NewsApiInterface::class.java)
}

fun createRssApi(okHttpClient: OkHttpClient): NewsApiInterface {

    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BuildConfig.BASE_URL)
        .client(okHttpClient)
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build()

    return retrofit.create(NewsApiInterface::class.java)
}