package com.filnik.goosegamekata

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.runner.AndroidJUnit4
import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.view.GameBoardScreen
import kotlinx.coroutines.flow.MutableStateFlow
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GameBoardScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `roll dice is present`() {
        rule.setContent {
            GameBoardScreen(Modifier, MutableStateFlow(listOf(Player(0, "Pippo", 0, true))))
        }

        rule.onNodeWithText("Roll Dice").assertIsDisplayed()
    }
}
