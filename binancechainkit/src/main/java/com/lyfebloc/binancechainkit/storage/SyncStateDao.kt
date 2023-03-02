package com.lyfebloc.binancechainkit.storage

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.lyfebloc.binancechainkit.models.SyncState

@Dao
interface SyncStateDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun update(syncState: SyncState)

    @Query("SELECT * FROM SyncState LIMIT 1")
    fun get(): SyncState?

}
