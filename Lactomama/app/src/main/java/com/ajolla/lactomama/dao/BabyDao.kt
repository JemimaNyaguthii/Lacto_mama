package com.ajolla.lactomama.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.ajolla.lactomama.model.BabyDataClass

@Dao
interface BabyDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertBaby(babyDataClass: BabyDataClass)

    @Query("SELECT * FROM baby")
    suspend fun getBaby(): List<BabyDataClass>

}