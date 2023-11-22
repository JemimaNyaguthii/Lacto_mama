package com.ajolla.lactomama.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.ajolla.lactomama.dao.StatusDao
import com.ajolla.lactomama.model.StatusDataClass

@Database(entities = [StatusDataClass::class], version = 1)
abstract class StatusDatabase : RoomDatabase() {
    abstract fun statusDao(): StatusDao

    companion object {
        @Volatile
        private var INSTANCE: StatusDatabase? = null

        fun getInstance(context: Context): StatusDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    StatusDatabase::class.java,
                    "status_database"
                ).build()

                INSTANCE = instance
                instance
            }
        }
    }
}