package com.example.data.entity

data class NewsJsonNM(
    var description: String?,
    var author: String?,
    var title: String,
    var publishedAt: String?,
    var content: String?,
    var url: String?,
    var image: String?,
    var urlToImage: String?,
    var source: SourceJsonNM,
    var artist: String?,
    var guid: String?
)

data class SourceJsonNM(
    var id : String?,
    var name : String
)
