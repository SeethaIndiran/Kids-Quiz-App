package com.example.kidsquiz.models

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken


class Converters
{


@TypeConverter
fun toQuizTypeJson(meaning: List<QuizType>) : String {
    return Gson().toJson(
        meaning,
        object : TypeToken<ArrayList<QuizType>>(){}.type
    ) ?: "[]"
}

    @TypeConverter
    fun fromQuizTypeJson(json: String): List<QuizType>{
        return Gson().fromJson<ArrayList<QuizType>>(
            json,
            object: TypeToken<ArrayList<QuizType>>(){}.type
        ) ?: emptyList()
    }

}