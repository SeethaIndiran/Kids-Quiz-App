package com.example.kidsquiz.db

import androidx.lifecycle.LiveData
import androidx.room.*
import com.example.kidsquiz.models.Player

@Dao
interface PlayerDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertPlayer(player: Player)

    @Delete
    suspend fun deletePlayer(player: Player)

    @Update
    suspend fun updatePlayer(player: Player)

    @Query("SELECT * FROM player_table")
    fun getAllPlayers():LiveData<List<Player>>

  @Query("SELECT * FROM player_table WHERE btnClicked = 1")
  fun getPlayer():LiveData<Player>

}