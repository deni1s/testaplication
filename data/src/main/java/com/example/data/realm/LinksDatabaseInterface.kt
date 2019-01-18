package com.example.data.realm

import com.example.model.Link

interface LinksDatabaseInterface {

    fun getLinkList() : List<Link>
    fun isLinkAlreadySaved(link: String?) : Boolean
    fun addLinkToDataBase(link: Link) {}
}