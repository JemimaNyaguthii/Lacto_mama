package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class UploadCoursesResponse(

    var title:String,
    var description:String,
    @SerializedName("start_date")var startDate:String,
    @SerializedName("end_date")var endDate:String,
    var price:String
)
