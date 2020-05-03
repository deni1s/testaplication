package com.example.data.module

import com.example.data.news.NewsJsonApi
import com.example.data.news.NewsXmlApi
import com.example.data.utils.NetworkResultCallAdapterFactory
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val BASE_URL = "https://newsapi.org"

val apiModule = module {
    // provided web components
    single { getNewHttpClient() }
    single { createJsonApi(get()) as NewsJsonApi }
    single { createXmlApi(get()) as NewsXmlApi }
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

fun createJsonApi(okHttpClient: OkHttpClient): NewsJsonApi {

    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResultCallAdapterFactory())
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    return retrofit.create(NewsJsonApi::class.java)
}

fun createXmlApi(okHttpClient: OkHttpClient): NewsXmlApi {

    val retrofit = retrofit2.Retrofit.Builder()
        .baseUrl(BASE_URL)
        .client(okHttpClient)
        .addCallAdapterFactory(NetworkResultCallAdapterFactory())
        .addConverterFactory(SimpleXmlConverterFactory.create())
        .build()

    return retrofit.create(NewsXmlApi::class.java)
}
