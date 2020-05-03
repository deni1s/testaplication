package com.example.data.news

import com.example.data.entity.NetworkResult
import com.example.data.response.NewsXmlResponse
import com.example.data.utils.AnnotatedConverterFactory
import retrofit2.http.GET
import retrofit2.http.Url

interface NewsXmlApi {
    @GET
    @AnnotatedConverterFactory.Xml
    suspend fun loadNews(@Url url: String): NetworkResult<NewsXmlResponse>
}
