package com.example.data.module

import com.example.data.news.NewsApiInterface
import com.example.data.news.NewsRepository
import com.example.data.utils.CoroutineCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit


val BASE_URL = "https://newsapi.org"

val apiModule = module {
    // provided web components
    single { getNewHttpClient() }
    single { NewsRepository(createJsonApi(get()), createRssApi(get())) }
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
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(NewsApiInterface::class.java)
}

fun createRssApi(okHttpClient: OkHttpClient): NewsApiInterface {

    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(CoroutineCallAdapterFactory())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    return retrofit.create(NewsApiInterface::class.java)
}