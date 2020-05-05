package com.example.repository.mappers

import com.example.entity.Link
import com.example.entity.LinkType
import com.example.storage.entity.LinkSM

fun LinkSM.toDomainModel(): Link {
    return Link(
        link = this.link,
        type = type.toDomainModel()
    )
}

fun List<LinkSM>.toDomainModel(): List<Link> {
    return map { it.toDomainModel() }
}

fun Link.toStorageModel(): LinkSM {
    return LinkSM(link = link, type = type.toStorageModel())
}

private fun String.toDomainModel(): LinkType {
    return when (this) {
        "json" -> LinkType.Json
        "xml" -> LinkType.Xml
        else -> LinkType.Invalid
    }
}

private fun LinkType.toStorageModel(): String {
    return when (this) {
        LinkType.Json -> "json"
        LinkType.Xml -> "xml"
        LinkType.Invalid -> "unknown"
    }
}
