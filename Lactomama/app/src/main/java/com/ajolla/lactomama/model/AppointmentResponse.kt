package com.ajolla.lactomama.model


data class AppointmentResponse(
    var appointments:List<appointmentdata>,
    var total:Int,
    var skip:Int,
    var limit:Int
)
