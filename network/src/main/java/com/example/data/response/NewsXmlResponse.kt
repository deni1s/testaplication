package com.example.data.response

import com.example.data.entity.NewsXmlNM
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "rss", strict = false)
data class NewsXmlResponse @JvmOverloads constructor(
    @field:Element(name = "channel")
    @param:Element(name = "channel")
    val channel: Channel
) {
    fun getNewsList(): List<NewsXmlNM>? {
        return if (channel.newsList?.isNullOrEmpty() == false) {
            channel.newsList
        } else {
            null
        }
    }
}


@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(entry = "item", inline = true) @param:ElementList(entry = "item", inline = true)
    val newsList: List<NewsXmlNM>? = emptyList()
)
