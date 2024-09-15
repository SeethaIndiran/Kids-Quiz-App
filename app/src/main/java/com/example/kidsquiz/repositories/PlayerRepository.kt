package com.example.kidsquiz.repositories

import androidx.lifecycle.LiveData
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.db.PlayerDao
import javax.inject.Inject

class  PlayerRepository @Inject constructor(val dao:PlayerDao) {

    suspend fun insertPlayer(player: Player) = dao.insertPlayer(player)

    suspend fun deletePlayer(player: Player) = dao.deletePlayer(player)

    suspend fun updatePlayer(player: Player) = dao.updatePlayer(player)

   fun  getAllPlayers(): LiveData<List<Player>> = dao.getAllPlayers()

    fun getPlayer():LiveData<Player> = dao.getPlayer()


}