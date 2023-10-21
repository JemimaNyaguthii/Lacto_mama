package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class LactationistRequest(
@SerializedName("first_name")var FirstName: String,
@SerializedName("second_name") var SecondName: String,
 val email: String,
val bio: String,
val password: String,
)

