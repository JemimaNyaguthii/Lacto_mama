package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class User(
    @SerializedName("username")var userName: String,
    val email: String,
    @SerializedName("first_name") var fullName: String,)

