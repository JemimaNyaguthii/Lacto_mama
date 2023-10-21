package com.ajolla.lactomama.model

data class LactationistResponse (
    var lactationists: List<Lactationist>,
    var total: Int,
    var skip: Int,
    var limit:String
)
