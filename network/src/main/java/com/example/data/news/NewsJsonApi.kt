package com.example.data.news

import com.example.data.entity.NetworkResult
import com.example.data.response.NewsJsonResponse
import com.example.data.utils.AnnotatedConverterFactory
import retrofit2.http.*

interface NewsJsonApi {
    @GET
    @AnnotatedConverterFactory.Json
    suspend fun loadNews(@Url url: String): NetworkResult<NewsJsonResponse>
}
