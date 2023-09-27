package com.ajolla.lactomama.model

data class Materials(
    val id: String,
    val name: String,
    val type: MaterialType,
    val url: String
)


enum class MaterialType {
    PDF,
    IMAGE,
    VIDEO,

}
