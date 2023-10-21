package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class Lactationist (
    @SerializedName("first_name")var firstName: String,
    val email: String,
    @SerializedName("second_name") var secondName: String,
    val password: String,
    val bio: String
)
