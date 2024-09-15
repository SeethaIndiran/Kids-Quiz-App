package com.example.kidsquiz.viewmodels

import androidx.lifecycle.ViewModel
import com.example.kidsquiz.models.Player
import com.example.kidsquiz.repositories.PlayerRepository
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PlayerViewmodl @Inject constructor(val repository: PlayerRepository):ViewModel(){

    fun insertPlayer(player: Player) = viewModelScope.launch {
        repository.insertPlayer(player)
    }

    fun deletePlayer(player: Player) = viewModelScope.launch {
        repository.deletePlayer(player)
    }

    fun updatePlayer(player: Player) = viewModelScope.launch {
        repository.updatePlayer(player)
    }

    fun getAllPlayers() = repository.getAllPlayers()

    fun getPlayer() = repository.getPlayer()


}