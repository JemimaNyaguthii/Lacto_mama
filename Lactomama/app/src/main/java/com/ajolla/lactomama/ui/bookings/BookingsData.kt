package com.ajolla.lactomama.ui.bookings

import com.google.gson.annotations.SerializedName

data class BookingsData(
    var need:String,
    @SerializedName("available_slot")var availableSlot :String
)
