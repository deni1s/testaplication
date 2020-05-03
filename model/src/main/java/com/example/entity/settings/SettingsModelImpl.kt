package com.example.entity.settings

import com.example.entity.Link
import com.example.entity.LinkType
import com.example.entity.Result
import com.example.repository.links.LinkRepository
import com.example.repository.news.NewsRepository

internal class SettingsModelImpl(
    val linkRepository: LinkRepository,
    val newsRepository: NewsRepository
) : SettingsModel {
    override suspend fun saveLink(linkUrl: String): Result<Unit> {
        val linkType = getLinkType(linkUrl)
        return if (linkType == LinkType.Invalid) {
            Result.Error(IllegalArgumentException("link type is invalid"))
        } else {
            linkRepository.saveLink(Link(type = linkType, link = linkUrl))
            Result.Success(Unit)
        }
    }

    private suspend fun getLinkType(linkUrl: String): LinkType {
        return when {
            newsRepository.loanJsonNews(linkUrl) is Result.Success -> LinkType.Json
            newsRepository.loanXmlNews(linkUrl) is Result.Success -> LinkType.Xml
            else -> LinkType.Invalid
        }
    }
}
