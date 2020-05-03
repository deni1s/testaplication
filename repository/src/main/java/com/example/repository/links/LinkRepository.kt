package com.example.repository.links

import com.example.entity.Link
import com.example.entity.Result

interface LinkRepository {
    suspend fun getLinkList(): List<Link>
    suspend fun saveLink(link: Link)
}
