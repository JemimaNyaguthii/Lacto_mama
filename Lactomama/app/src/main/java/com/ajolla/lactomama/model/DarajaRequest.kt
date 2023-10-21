package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class DarajaRequest(
@SerializedName("phone_number")var phoneNumber:String,
    var amount:Int,
)
