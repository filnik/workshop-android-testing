package com.filnik.goosegamekata

import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.viewmodel.MainViewModel
import io.kotest.matchers.shouldBe
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.DynamicTest
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.TestFactory

class MainViewModelTest {
    private lateinit var viewModel: MainViewModel
    private val PIPPO = Player(0, "Pippo", 0, true)
    private val PLUTO = Player(1, "Pluto", 0, false)

    @BeforeEach
    fun setUp() {
        viewModel = MainViewModel()
    }

    // The single tests below should be done at first to tackle all the single use cases

    @Test
    fun `WHEN starting, player list should be empty`() {
        viewModel.players.value shouldBe emptyList()
    }

    @Test
    fun `SHOULD add one single player`() {
        viewModel.addPlayer("Pippo")

        viewModel.players.value shouldBe listOf(PIPPO)
    }

    @Test
    fun `SHOULD add two players`() {
        viewModel.addPlayer("Pippo")
        viewModel.addPlayer("Pluto")

        viewModel.players.value shouldBe listOf(PIPPO, PLUTO)
    }

    // The tests above can be summarized by the TestFactory here below in order to optimize
    // for maintenance

    @TestFactory
    fun `SHOULD add player`() =
        listOf(
            listOf<String>() to listOf(),
            listOf("Pippo") to listOf(PIPPO),
            listOf("Pippo", "Pluto") to listOf(PIPPO, PLUTO),
        ).map { (playerNames, result) ->
            DynamicTest.dynamicTest("WHEN player names are $playerNames") {
                viewModel = MainViewModel()

                playerNames.forEach {
                    viewModel.addPlayer(it)
                }

                viewModel.players.value shouldBe result
            }
        }
}
