package com.ajolla.lactomama.mother

import android.widget.TextView
import java.io.Serializable
data class CoursesData(
    var image:String,
    var name: String,
    var description:String,
    var price:String,
    var period:String
):Serializable
