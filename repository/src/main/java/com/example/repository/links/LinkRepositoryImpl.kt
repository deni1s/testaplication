package com.example.repository.links

import com.example.entity.Link
import com.example.repository.mappers.toDomainModel
import com.example.repository.mappers.toStorageModel
import com.example.storage.links.LinkDao
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class LinkRepositoryImpl @Inject constructor(private val linkDao: LinkDao) : LinkRepository {
    override suspend fun getLinkList(): Flow<List<Link>> {
        return linkDao.getLinkList().map { it.toDomainModel() }
    }

    override suspend fun saveLink(link: Link) {
        linkDao.addLinkToDataBase(link.toStorageModel())
    }

    override suspend fun linkAlreadySaved(linkUrl: String): Boolean {
        return linkDao.getLinkByUrl(linkUrl) != null
    }
}
