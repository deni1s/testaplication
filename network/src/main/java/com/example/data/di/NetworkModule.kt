package com.example.data.di

import com.example.data.news.NewsXmlApi
import com.example.data.utils.NetworkResultCallAdapterFactory
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.simplexml.SimpleXmlConverterFactory
import java.util.concurrent.TimeUnit

@Module
class NetworkModule {

    @Provides
    fun provideOkHttpClient(): OkHttpClient {
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

    @Provides
    fun provideRetrofitBuilder(okHttpClient: OkHttpClient): Retrofit.Builder {
        return Retrofit.Builder()
            .baseUrl(BASE_URL)
            .client(okHttpClient)
    }

    @Provides
    fun provideXmlApi(retrofit: Retrofit.Builder): NewsXmlApi {
        return retrofit
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(NewsXmlApi::class.java)
    }

    @Provides
    fun provideJsonApi(retrofit: Retrofit.Builder): NewsXmlApi {
        return retrofit
            .addCallAdapterFactory(NetworkResultCallAdapterFactory())
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .build()
            .create(NewsXmlApi::class.java)
    }
}

val BASE_URL = "https://newsapi.org"
