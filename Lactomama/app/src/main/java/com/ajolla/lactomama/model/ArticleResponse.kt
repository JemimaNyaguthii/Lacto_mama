package com.ajolla.lactomama.model

import com.google.gson.annotations.SerializedName

data class ArticleResponse(
    var message: String,
    var educationalMaterialResponse: EducationalMaterialResponse

)
