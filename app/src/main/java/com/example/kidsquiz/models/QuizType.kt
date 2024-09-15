package com.example.kidsquiz.models

import java.io.Serializable

data class QuizType(
    val count:Int,
    var name:String,
    var quiz_type_score: Int,
    val quizCategory:List<Category>
        ):Serializable