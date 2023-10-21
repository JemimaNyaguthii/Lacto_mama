package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class Result(
    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("items_name")
    var name: String? = null,

    @SerializedName("price")
    var price: Double = 0.0,

    @SerializedName("number_of_items")
    var numberOfItems: Int = 0,

    @SerializedName("discount")
    var discount: Double = 0.0,

    @SerializedName("description")
    var description: String? = null
)
