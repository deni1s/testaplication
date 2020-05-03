package com.example.entity

data class Link(
    var link: String,
    var type: LinkType
)

enum class LinkType {
    Json,
    Xml,
    Invalid
}
