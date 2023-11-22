package com.ajolla.lactomama.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "baby")
data class BabyDataClass(
    @PrimaryKey(autoGenerate = false)
    val babyid : Int = 0,
    val babyFullName :String,
    val birthDate: String,
    val timeOfBirth :String,
    val dueDate :String,
    val babyGender :String,
    val babyProfileImage :ByteArray,
)

