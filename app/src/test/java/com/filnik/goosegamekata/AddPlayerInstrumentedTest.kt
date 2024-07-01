package com.filnik.goosegamekata

import androidx.compose.ui.test.assertIsDisplayed
import androidx.compose.ui.test.junit4.createComposeRule
import androidx.compose.ui.test.onNodeWithText
import androidx.compose.ui.test.performClick
import androidx.compose.ui.test.performTextInput
import androidx.test.core.app.ActivityScenario
import androidx.test.ext.junit.runners.AndroidJUnit4
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.shadows.ShadowLog

@RunWith(AndroidJUnit4::class)
class AddPlayerInstrumentedTest {
    @get:Rule
    val composeTestRule = createComposeRule()

    @Before
    @Throws(Exception::class)
    fun setUp() {
        ShadowLog.stream = System.out // Redirect Logcat to console
    }

    @Test
    fun `add player works correctly`() {
        val scenario = ActivityScenario.launch(MainActivity::class.java)

        scenario.onActivity { activity ->
            composeTestRule.onNodeWithText("Player Name").assertIsDisplayed().performTextInput("Pippo")
            composeTestRule.onNodeWithText("Add Player").performClick()

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithText("Player list: Pippo").assertIsDisplayed()

            composeTestRule.onNodeWithText("Player Name").assertIsDisplayed().performTextInput("Pluto")
            composeTestRule.onNodeWithText("Add Player").performClick()

            composeTestRule.waitForIdle()

            composeTestRule.onNodeWithText("Player list: Pippo, Pluto").assertIsDisplayed()
        }
    }
}
