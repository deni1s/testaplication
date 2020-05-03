package com.example.storage.links

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.storage.entity.LinkSM

@Dao
interface LinkDao {
    @Query("SELECT * FROM links_table ")
    suspend fun getLinkList(): List<LinkSM>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addLinkToDataBase(link: LinkSM)
}
