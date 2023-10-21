package com.ajolla.lactomama.ui

import android.icu.text.CaseMap.Title
import com.google.gson.annotations.SerializedName

data class EducationalMaterialData(
    val id:Int,
    val title: String,
    val description: String,
    @SerializedName("created_at")val createdAt: String,
    @SerializedName("updated_at")val updatedAt: String,
    val content: String,
    val image:String
)


