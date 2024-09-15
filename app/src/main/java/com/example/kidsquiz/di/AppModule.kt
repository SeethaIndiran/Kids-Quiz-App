package com.example.kidsquiz.di

import android.content.Context
import android.content.SharedPreferences
import androidx.room.Room
import com.example.kidsquiz.common.Constants
import com.example.kidsquiz.common.Constants.KEY_FIRST_TIME_TOGGLE
import com.example.kidsquiz.common.Constants.SHARED_PREFERENCES_NAME
import com.example.kidsquiz.db.PlayerDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

@Singleton
@Provides
fun getPlayerDatabase(@ApplicationContext app:Context) =
    Room.databaseBuilder(app,PlayerDatabase::class.java,Constants.PLAYER_DATABASE)
        .build()

    @Singleton
    @Provides
    fun getPlayerDao(db:PlayerDatabase) = db.getPlayerDao()



    @Singleton
    @Provides
    fun provideSharedPreferences(@ApplicationContext app:Context) =
        app.getSharedPreferences(SHARED_PREFERENCES_NAME, Context.MODE_PRIVATE)

    @Singleton
    @Provides
    fun provideFirstTimeToggle(sharedPref:SharedPreferences) = sharedPref.getBoolean(
        KEY_FIRST_TIME_TOGGLE,true)
}