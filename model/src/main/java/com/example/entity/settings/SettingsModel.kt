package com.example.entity.settings

import com.example.entity.Result

interface SettingsModel {
    suspend fun saveLink(linkUrl: String): Result<Unit>
}
