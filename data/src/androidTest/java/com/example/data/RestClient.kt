package com.example.data

import com.example.data.module.BASE_URL
import com.example.data.news.NewsApiInterface
import com.example.data.utils.CoroutineCallAdapterFactory
import retrofit2.Retrofit
import okhttp3.OkHttpClient
import retrofit2.converter.gson.GsonConverterFactory


class RestClient {
    private var mRestService: NewsApiInterface? = null

    fun getJsonClient(string: String): NewsApiInterface? {
        if (mRestService == null) {
            val client = OkHttpClient()
            client.interceptors().add(FakeInterceptor(string))

            val retrofit = Retrofit.Builder()
                // Using custom Jackson Converter to parse JSON
                // Add dependencies:
                // com.squareup.retrofit:converter-jackson:2.0.0-beta2
                .addCallAdapterFactory(CoroutineCallAdapterFactory())
                .addConverterFactory(GsonConverterFactory.create())                // Endpoint
                .baseUrl(BASE_URL)
                .client(client)
                .build()

            mRestService = retrofit.create(NewsApiInterface::class.java!!)
        }
        return mRestService
    }
}