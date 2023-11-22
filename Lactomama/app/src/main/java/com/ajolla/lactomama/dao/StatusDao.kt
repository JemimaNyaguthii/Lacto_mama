package com.ajolla.lactomama.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajolla.lactomama.model.StatusDataClass

@Dao
interface StatusDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertStatus(sleepDataClass: StatusDataClass)

    @Query("SELECT * FROM all_data")
    suspend fun getStatus(): List<StatusDataClass>
}