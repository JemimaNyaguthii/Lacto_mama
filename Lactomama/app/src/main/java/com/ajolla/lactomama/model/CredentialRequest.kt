package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class CredentialRequest(

   @SerializedName( "lisense_number") var lnumber:String,
   @SerializedName( "issued_by") var issuedBy:String,
   @SerializedName( "date_issued") var dateIssued: String,
)






