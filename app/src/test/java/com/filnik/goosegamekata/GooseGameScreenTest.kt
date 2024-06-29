package com.filnik.goosegamekata

import androidx.compose.ui.Modifier
import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.filnik.goosegamekata.view.GameBoardScreen
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class GooseGameScreenTest {
    @get:Rule
    val rule = createComposeRule()

    @Test
    fun `test`() {
        rule.setContent {
            GameBoardScreen(Modifier)
        }

        rule.onNodeWithText("Roll Dice").assertIsDisplayed()
    }
}
