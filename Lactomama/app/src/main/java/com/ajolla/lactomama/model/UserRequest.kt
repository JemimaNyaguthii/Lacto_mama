package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class UserRequest (
    @SerializedName("username")var userName: String,
    val email: String,
    @SerializedName("first_name") var fullName: String,
    val password: String,
)
