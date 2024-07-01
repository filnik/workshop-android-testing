package com.filnik.goosegamekata

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.view.AddPlayerScreen
import io.mockk.mockk
import io.mockk.verify
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class AddPlayerScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `Players list is empty at first`() {
        rule.setContent {
            AddPlayerScreen(
                navigateToNextScreen = {},
                playersModel = listOf(),
                errorMessage = null,
                addPlayer = {},
            )
        }

        rule.onNodeWithText("Player list: ").assertIsDisplayed()
    }

    @Test
    fun `Players list shows players when added`() {
        rule.setContent {
            AddPlayerScreen(
                navigateToNextScreen = {},
                playersModel = listOf(Player(0, "Pippo", 9), Player(1, "Pluto", 2, true)),
                errorMessage = null,
                addPlayer = {},
            )
        }

        rule.onNodeWithText("Player list: Pippo, Pluto").assertIsDisplayed()
    }

    @Test
    fun `Add Player button is present and works as expected`() {
        val addPlayer: (String) -> Unit = mockk(relaxed = true)

        rule.setContent {
            AddPlayerScreen(
                navigateToNextScreen = {},
                playersModel = listOf(),
                errorMessage = null,
                addPlayer = addPlayer,
            )
        }

        rule.onNodeWithText("Player Name").performTextInput("Pippo")
        rule.onNodeWithText("Add Player").assertIsDisplayed().performClick()
        verify { addPlayer.invoke("Pippo") }

        rule.onNodeWithText("Player Name").performTextInput("Pluto")
        rule.onNodeWithText("Add Player").assertIsDisplayed().performClick()
        verify { addPlayer.invoke("Pluto") }
    }
}
