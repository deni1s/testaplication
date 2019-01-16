package com.example.data.news

import com.example.data.response.NewsResponse
import com.example.data.utils.AnnotatedConverterFactory
import kotlinx.coroutines.Deferred
import retrofit2.http.*


interface  NewsApiInterface {

    @GET
    @AnnotatedConverterFactory.Json
    fun loadJsonNews(@Url url: String): Deferred<NewsResponse>

    @GET
    @AnnotatedConverterFactory.Xml
    fun loadRssNews(@Url url: String): Deferred<NewsResponse>
}