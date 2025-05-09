package com.filnik.goosegamekata.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.itemsIndexed
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.model.PlayerUI
import com.filnik.goosegamekata.model.toUI

@Composable
fun GameBoardScreen(
    modifier: Modifier,
    players: List<Player> = emptyList(),
    dice: List<Int> = listOf(1, 2),
    rollDice: () -> Unit = {},
) {
    val uiPlayers = players.map { player -> player.toUI() }
    val die1 = dice[0]
    val die2 = dice[1]

    Column(
        modifier =
        modifier
            .fillMaxSize()
            .padding(16.dp)
            .background(Color(0xFFF4E7F5)),
    ) {
        Text(
            text = "Goose Game",
            style = MaterialTheme.typography.headlineLarge.copy(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
            ),
            modifier = Modifier.padding(start = 4.dp, bottom = 16.dp),
        )

        LazyVerticalGrid(
            columns = GridCells.Fixed(10),
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(4.dp),
        ) {
            itemsIndexed((1..63).toList()) { index, cellNumber ->
                val player = uiPlayers.find { it.position == cellNumber }
                if (player != null) {
                    DrawPlayerCell(player)
                } else {
                    DrawCellWithNumber(index, cellNumber)
                }
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Row(
            modifier =
            Modifier
                .fillMaxWidth()
                .height(130.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
        ) {
            uiPlayers.forEach { player ->
                if (player.rollDice) {
                    val startingPosition = player.position - die1 - die2
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally,
                    ) {
                        Text(
                            "${player.name} rolls $die1, $die2.\n${player.name} moves from $startingPosition to ${player.position}",
                            fontSize = 16.sp,
                            textAlign = TextAlign.Center,
                            fontWeight = FontWeight.Medium,
                            color = Color(0xFF333333),
                        )
                        Button(
                            onClick = rollDice,
                            modifier = Modifier.padding(top = 8.dp),
                            colors = ButtonDefaults.buttonColors(containerColor = Color(0xFFD48EDB)),
                        ) {
                            Text("Roll Dice", color = Color.White, fontSize = 14.sp)
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun DrawPlayerCell(player: PlayerUI) {
    Image(
        painter = painterResource(id = player.image),
        contentDescription = null,
        modifier =
        Modifier
            .fillMaxSize()
            .padding(4.dp),
    )
}

@Composable
private fun DrawCellWithNumber(index: Int, cellNumber: Int) {
    val cellColor = if (index % 2 == 0) Color(0xFFB3E5FC) else Color(0xFFFFC1C1)

    Box(
        modifier =
        Modifier
            .size(40.dp)
            .padding(4.dp)
            .background(cellColor)
            .border(1.dp, Color(0xFFDDDDDD)),
        contentAlignment = Alignment.Center,
    ) {
        Text(
            text = cellNumber.toString(),
            fontSize = 12.sp,
            color = Color(0xFF333333),
        )
    }
}

@Preview(showBackground = true, heightDp = 550)
@Composable
fun GameBoardPreview() {
    val players = listOf(Player(0, "Player 1", 3), Player(1, "Player 2", 10, true))
    val dice = listOf(6, 4)
    GameBoardScreen(Modifier, players, dice)
}
