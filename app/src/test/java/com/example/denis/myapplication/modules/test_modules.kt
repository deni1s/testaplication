package com.example.denis.myapplication.modules

import com.example.denis.myapplication.BuildConfig
import com.example.denis.myapplication.repositories.news.NewsApiInterface
import com.example.denis.myapplication.repositories.news.NewsRepository
import com.example.denis.myapplication.repositories.realm.LinksRealmRepository
import com.example.denis.myapplication.repositories.realm.NewsRealmRepository
import com.example.denis.myapplication.utils.TestSchedulerProvider
import com.example.denis.myapplication.utils.coroutines.SchedulerProvider
import io.realm.Realm
import okhttp3.OkHttpClient
import org.koin.dsl.module.module
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

val remoteDataSourceModule = module {
    // provided web components
    single { getNewHttpClient() }
    single { NewsRepository(createJsonApi(get()), createRssApi(get())) }
}

val realmModule = module {

    single { getRealmInstance() }
    single { LinksRealmRepository(get()) }
    single { NewsRealmRepository(get()) }
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

fun getRealmInstance(): Realm {
    return Realm.getDefaultInstance()
}

val testRxModule = module(override = true) {
    // provided components
    single<SchedulerProvider> { TestSchedulerProvider() }
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

val testNewsApp = listOf(realmModule, remoteDataSourceModule, testRxModule)
