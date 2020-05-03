package com.example.repository.links

import com.example.entity.Link
import com.example.repository.mappers.toDomainModel
import com.example.repository.mappers.toStorageModel
import com.example.storage.links.LinkDao

internal class LinkRepositoryImpl(val linkDao: LinkDao) : LinkRepository {
    override suspend fun getLinkList(): List<Link> {
        return linkDao.getLinkList().toDomainModel()
    }

    override suspend fun saveLink(link: Link) {
        linkDao.addLinkToDataBase(link.toStorageModel())
    }
}
