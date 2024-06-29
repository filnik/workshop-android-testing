package com.filnik.goosegamekata.viewmodel

import androidx.lifecycle.ViewModel
import com.filnik.goosegamekata.model.Player
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {
    val players = listOf(Player(0, "Player 1", 9), Player(1, "Player 2", 2, true))
}
