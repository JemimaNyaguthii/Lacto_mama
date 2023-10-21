package com.ajolla.lactomama.ui.home

import android.icu.text.CaseMap.Title
import android.media.Image

data class ArticleData(
    val id: Int,
    val title: String,
    val description: String,
    val createdAt: String,
    val updatedAt: String,
    val content: String,
    val image: String?
)
