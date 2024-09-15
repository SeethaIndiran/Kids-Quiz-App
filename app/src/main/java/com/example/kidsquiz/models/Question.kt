package com.example.kidsquiz.models

import java.io.Serializable

data class Question (
    val title:String,
    val image:Int,
    val optionOne:String,
    val optionTwo:String,
    val optionThree:String,
    val optionFour:String,
    val correctOption:Int

    ):Serializable