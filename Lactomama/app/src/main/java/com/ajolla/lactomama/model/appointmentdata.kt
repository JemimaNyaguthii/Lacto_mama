package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class appointmentdata(
    var lactationist: String,
    @SerializedName("available_slot") var availableDate: String,
    @SerializedName("is_available") var available: String,
    var need: String,
    @SerializedName("first_name") var firstName: String,
    @SerializedName("second_name") var secondName: String,
    var mother: Int,
    )
