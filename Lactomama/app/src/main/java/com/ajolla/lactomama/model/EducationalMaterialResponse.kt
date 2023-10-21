package com.ajolla.lactomama.model

import com.ajolla.lactomama.ui.EducationalMaterialData

data class EducationalMaterialResponse(
    var educationalMaterials:List<EducationalMaterialData>,
    var total:Int,
    var skip:Int,
    var limit:Int
)
