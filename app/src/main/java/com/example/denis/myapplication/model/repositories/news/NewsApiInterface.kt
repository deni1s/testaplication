package com.example.denis.myapplication.model.repositories.news

import com.example.denis.myapplication.model.parsing.AnnotatedConverterFactory
import com.example.denis.myapplication.model.classes.responses.NewsResponse
import io.reactivex.Single
import retrofit2.http.*


interface   NewsApiInterface {

    @GET
    @AnnotatedConverterFactory.Json
    fun loadJsonNews(@Url url: String): Single<NewsResponse>

    @GET
    @AnnotatedConverterFactory.Xml
    fun loadRssNews(@Url url: String): Single<NewsResponse>
}