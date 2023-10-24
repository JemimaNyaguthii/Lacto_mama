package com.ajolla.lactomama.mother.cart

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class Course(
    val id: Int,
    val title: String,
    @SerializedName("items_name") val itemsName: String,
    @SerializedName("number_of_items") val numberOfItems: String,
    val price: Double,
    val description: String,
    val discount: Double,
)