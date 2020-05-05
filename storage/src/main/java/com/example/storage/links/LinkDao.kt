package com.example.storage.links

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.entity.LinkSM
import kotlinx.coroutines.flow.Flow

@Dao
interface LinkDao {
    @Query("SELECT * FROM links_table ")
    fun getLinkList(): Flow<List<LinkSM>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLinkToDataBase(link: LinkSM)

    @Query("SELECT * FROM links_table WHERE link = :linkUrl")
    suspend fun getLinkByUrl(linkUrl: String): LinkSM?
}
