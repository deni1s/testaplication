package com.example.data.response

import com.example.model.News
import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root


@Root(name = "rss", strict = false)
data class NewsResponse(
    @field:Element(name = "channel") @param:Element(name = "channel")
    var channel: Channel? = null,

    @Root(strict = false)
    var articles: List<News>? = arrayListOf()
) {
    fun getNewsList(): List<News>? {
        if (articles != null && articles!!.size != 0) {
            return articles!!
        } else if (channel != null && channel!!.newsList != null && channel!!.newsList!!.size != 0) {
            return channel!!.newsList!!
        } else {
            return null
        }
    }

    fun isSuccessful(): Boolean {
        return getNewsList() != null
    }
}


@Root(name = "channel", strict = false)
data class Channel @JvmOverloads constructor(
    @field:ElementList(entry = "item", inline = true) @param:ElementList(entry = "item", inline = true)
    val newsList: List<News>? = null
)