package com.filnik.goosegamekata.view

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.model.toUI
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.map

@Composable
fun AddPlayerScreen(
    modifier: Modifier = Modifier,
    navigateToNextScreen: () -> Unit,
    playersFlow: StateFlow<List<Player>>,
    errorMessageFlow: StateFlow<String?>,
    addPlayer: (String) -> Unit,
) {
    val playerName = remember { mutableStateOf("") }
    val players by playersFlow.map { it.map { player -> player.toUI().name } }.collectAsState(initial = emptyList())
    val errorMessage by errorMessageFlow.collectAsState()

    Column(
        modifier = modifier
            .padding(16.dp)
            .fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        if (errorMessage != null) {
            Text(
                text = errorMessage ?: "",
                color = Color.Red,
                modifier = Modifier.padding(top = 8.dp),
            )
        } else {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Player list: ${players.joinToString(", ")}",
                style = MaterialTheme.typography.bodyLarge,
            )
        }
        Spacer(modifier = Modifier.height(16.dp))
        TextField(
            value = playerName.value,
            onValueChange = { playerName.value = it },
            label = { Text("Player Name") },
        )
        Spacer(modifier = Modifier.height(8.dp))
        Button(
            onClick = {
                addPlayer(playerName.value)
                playerName.value = ""
            },
        ) {
            Text("Add Player")
        }
        Spacer(modifier = Modifier.weight(1F))
        Button(
            onClick = navigateToNextScreen,
        ) {
            Text("Next")
        }
    }
}

@Preview(showBackground = true, heightDp = 300)
@Composable
fun AddPlayerScreenPreview() {
    AddPlayerScreen(
        navigateToNextScreen = {},
        playersFlow = MutableStateFlow(listOf(Player(0, "Player 1", 9), Player(1, "Player 2", 2, true))),
        errorMessageFlow = MutableStateFlow(null),
        addPlayer = {},
    )
}

@Preview(showBackground = true, heightDp = 300)
@Composable
fun AddPlayerScreenPreviewWithError() {
    AddPlayerScreen(
        navigateToNextScreen = {},
        playersFlow = MutableStateFlow(listOf(Player(0, "Player 1", 9), Player(1, "Player 2", 2, true))),
        errorMessageFlow = MutableStateFlow("Error"),
        addPlayer = {},
    )
}
