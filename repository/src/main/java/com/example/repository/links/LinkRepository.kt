package com.example.repository.links

import com.example.entity.Link
import kotlinx.coroutines.flow.Flow

interface LinkRepository {
    suspend fun getLinkList(): Flow<List<Link>>
    suspend fun saveLink(link: Link)
}
