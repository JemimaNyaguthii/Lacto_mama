package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName
import java.time.LocalDate
import java.util.Date

data class Credential(
    @SerializedName( "lisense_number") var lnumber :String,
    @SerializedName( "issued_by") var issuedBy :String,
    @SerializedName( "date_issued")var dateIssued: LocalDate,


)
