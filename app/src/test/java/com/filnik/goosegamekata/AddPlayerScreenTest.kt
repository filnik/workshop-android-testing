package com.filnik.goosegamekata

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.view.AddPlayerScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddPlayerScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `Add player is present`() {
        rule.setContent {
            AddPlayerScreen(
                navigateToNextScreen = {},
                playersModel = listOf(Player(0, "Player 1", 9), Player(1, "Player 2", 2, true)),
                errorMessage = null,
                addPlayer = {},
            )
        }

        // Hint for debug:
        // print(rule.onRoot().printToString())

        rule.onNodeWithText("Add Player").assertIsDisplayed()
    }
}
