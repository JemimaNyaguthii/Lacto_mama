package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class Product(
    @SerializedName("description")
    var description: String? = null,

    @SerializedName("id")
    var id: Int = 0,

    @SerializedName("name")
    var name: String? = null,

    @SerializedName("price")
    var price: Double = 0.0,

)
