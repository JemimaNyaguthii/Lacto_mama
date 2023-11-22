package com.ajolla.lactomama.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import com.ajolla.lactomama.dao.BabyDao
import com.ajolla.lactomama.model.BabyDataClass

@Database(entities = [BabyDataClass::class], version = 1)
abstract class BabyDatabase :RoomDatabase() {

    abstract fun babyDao() : BabyDao

    companion object{
        @Volatile
        private var instance : BabyDatabase? =null

        fun getDatabase(context : Context) : BabyDatabase {
            return instance ?: synchronized(this){
                val database = Room.databaseBuilder(
                    context.applicationContext,
                    BabyDatabase::class.java,
                    "baby_database"
                ).build()

                instance = database
                database
            }
        }
    }

}