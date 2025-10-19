package com.filnik.goosegamekata.model

import androidx.annotation.DrawableRes
import com.filnik.goosegamekata.R

data class Player(val id: Int = 0, val name: String, var position: Int = 0, val rollDice: Boolean = false)

fun Player.toUI(): PlayerUI {
    val image =
        when (id) {
            0 -> R.drawable.checker0
            1 -> R.drawable.checker1
            2 -> R.drawable.checker2
            3 -> R.drawable.checker3
            4 -> R.drawable.checker4
            5 -> R.drawable.checker0
            else -> R.drawable.checker1
        }
    return PlayerUI(name, position, rollDice, image)
}

data class PlayerUI(val name: String, var position: Int = 0, val rollDice: Boolean, @DrawableRes val image: Int = R.drawable.checker1)
