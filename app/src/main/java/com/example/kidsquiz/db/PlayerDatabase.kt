package com.example.kidsquiz.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.kidsquiz.models.Converters
import com.example.kidsquiz.models.Player

@Database(entities = [Player::class], version = 1)
@TypeConverters(Converters::class)
abstract class PlayerDatabase:RoomDatabase() {

    abstract fun getPlayerDao():PlayerDao
}