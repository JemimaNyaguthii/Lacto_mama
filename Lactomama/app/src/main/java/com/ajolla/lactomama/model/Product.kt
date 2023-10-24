package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class Product(
    val id: Int,
    val title: String,
    @SerializedName("items_name")
    val itemsName: String,
    @SerializedName("number_of_items")
    val numberOfItems: String,
    val price: Double,
    val description: String,
    val discount: Double
)