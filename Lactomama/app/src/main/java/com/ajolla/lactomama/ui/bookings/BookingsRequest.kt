package com.ajolla.lactomama.ui.bookings

import com.google.gson.annotations.SerializedName

data class BookingsRequest(
    @SerializedName("available_slot")var availableSlot : String,
    @SerializedName("first_name")var firstName:String,
    var need: String,
    var lactationist:Int,

    )
