package com.example.denis.myapplication.repositories.news

import com.example.denis.myapplication.data.responses.NewsResponse
import com.example.denis.myapplication.utils.parsing.AnnotatedConverterFactory
import io.reactivex.Single
import retrofit2.http.*


interface  NewsApiInterface {

    @GET
    @AnnotatedConverterFactory.Json
    fun loadJsonNews(@Url url: String): Single<NewsResponse>

    @GET
    @AnnotatedConverterFactory.Xml
    fun loadRssNews(@Url url: String): Single<NewsResponse>
}