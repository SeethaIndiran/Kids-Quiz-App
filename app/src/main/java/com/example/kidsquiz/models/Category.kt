package com.example.kidsquiz.models

import java.io.Serializable

data class Category(
    val categoryName: String,
    var quiz_category_score: Int,
    val question:List<Question>

):Serializable