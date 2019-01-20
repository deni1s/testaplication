package com.example.data.models

import com.example.data.news.NewsApiInterface
import com.example.data.utils.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.converter.simplexml.SimpleXmlConverterFactory


class RestClient {
    private var mRestService: NewsApiInterface? = null

    fun getClient(stringFromFile: String, isJson: Boolean): NewsApiInterface? {
        if (mRestService == null) {
            val client = OkHttpClient.Builder()
                .addInterceptor(FakeInterceptor(stringFromFile))
                .build()

            if (isJson) {
                return getJsonRetrofit(client)
            } else {
                return getRssRetrofit(client)
            }


        }
        return mRestService
    }

    private fun getJsonRetrofit(client: OkHttpClient): NewsApiInterface? {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://mock.api")
            .client(client)
            .build()

        mRestService = retrofit.create<NewsApiInterface>(NewsApiInterface::class.java)
        return mRestService
    }

    private fun getRssRetrofit(client: OkHttpClient): NewsApiInterface? {
        val retrofit = Retrofit.Builder()
            .addConverterFactory(SimpleXmlConverterFactory.create())
            .addCallAdapterFactory(CoroutineCallAdapterFactory())
            .baseUrl("http://mock.api")
            .client(client)
            .build()

        mRestService = retrofit.create<NewsApiInterface>(NewsApiInterface::class.java)
        return mRestService
    }
}