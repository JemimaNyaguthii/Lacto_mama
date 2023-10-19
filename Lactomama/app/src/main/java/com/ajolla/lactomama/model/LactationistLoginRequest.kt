package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class LactationistLoginRequest(
    var email : String,
    var password : String,
    var success: Boolean,
)
