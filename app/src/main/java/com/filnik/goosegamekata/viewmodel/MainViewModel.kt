package com.filnik.goosegamekata.viewmodel

import androidx.lifecycle.ViewModel
import com.filnik.goosegamekata.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val dice = MutableStateFlow(listOf(2, 6))
    val errorMessage: MutableStateFlow<String?> = MutableStateFlow(null)
    val players = MutableStateFlow(listOf(Player(0, "Player 1", 9), Player(1, "Player 2", 2, true)))

    fun addPlayer(playerName: String) {
        players.value = listOf(Player(0, "Pippo", 0, true))
    }

    fun rollDice() {
    }
}
