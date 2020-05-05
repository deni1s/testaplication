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
        if (linkAlreadySaved(linkUrl)) {
            return Result.Error(IllegalArgumentException("link already saved"))
        }
        val linkType = getLinkType(linkUrl)
        return if (linkType == LinkType.Invalid) {
            Result.Error(IllegalArgumentException("link type is invalid"))
        } else {
            linkRepository.saveLink(Link(type = linkType, link = linkUrl))
            Result.Success(Unit)
        }
    }

    override suspend fun saveLink(link: Link): Result<Unit> {
        linkRepository.saveLink(link)
        return Result.Success(Unit)
    }

    override suspend fun getLinkList(): Result<List<Link>> {
        val linkList = mutableListOf<Link>()
        fakeLinkList.map {
            if (!linkAlreadySaved(it.link)) {
                linkList.add(it)
            }
        }
        return Result.Success(linkList)
    }

    private suspend fun getLinkType(linkUrl: String): LinkType {
        return when {
            newsRepository.loanJsonNews(linkUrl) is Result.Success -> LinkType.Json
            newsRepository.loanXmlNews(linkUrl) is Result.Success -> LinkType.Xml
            else -> LinkType.Invalid
        }
    }

    private suspend fun linkAlreadySaved(linkUrl: String): Boolean {
        return linkRepository.linkAlreadySaved(linkUrl)
    }
}

private val fakeLinkList = listOf(
    Link(
        link = "http://newsapi.org/v2/top-headlines?country=us&category=business&apiKey=2778ba9d0314458596009ce5650f906f",
        type = LinkType.Json
    ),
    Link(
        link = "https://www.buzzfeed.com/world.xml",
        type = LinkType.Xml
    )
)
