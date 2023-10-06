package com.ajolla.lactomama.model

data class UploadCoursesRequest(
    var title: String,
    var description: String,
    var start_date: String,
    var end_date: String,
    var price: String
)
