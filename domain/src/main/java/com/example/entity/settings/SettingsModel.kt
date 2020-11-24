package com.example.entity.settings

import com.example.entity.Link
import com.example.entity.Result

interface SettingsModel {
    suspend fun saveLink(linkUrl: String): Result<Unit>
    suspend fun saveLink(link: Link): Result<Unit>
    suspend fun getLinkList(): Result<List<Link>>
}
