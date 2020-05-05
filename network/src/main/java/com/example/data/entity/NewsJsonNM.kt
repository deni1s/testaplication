package com.example.data.entity

data class NewsJsonNM(
    var description: String?,
    var author: String?,
    var title: String,
    var publishedAt: String,
    var content: String?,
    var url: String?,
    var urlToImage: String?,
    var source: SourceJsonNM
)

data class SourceJsonNM(
    var id: String?,
    var name: String
)
