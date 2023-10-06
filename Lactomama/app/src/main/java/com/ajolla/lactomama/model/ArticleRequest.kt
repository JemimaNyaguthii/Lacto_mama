package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class ArticleRequest(
    val id: Int,
    val title: String,
    val description: String,
    @SerializedName("created_at") val createdAt: String,
    @SerializedName("updated_at") val updatedAt: String,
    val content: String,
    @SerializedName("lactationist") val lactationist: String
)
