package com.example.kidsquiz.models

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "player_table")
data class Player(

    @ColumnInfo var name:String,
    @ColumnInfo var totalStarCount:Int,
    @ColumnInfo var quiz_type:List<QuizType>,
    @ColumnInfo var btnClicked:Boolean = false,
    @PrimaryKey(autoGenerate = true) val id: Int = 0

) :Serializable