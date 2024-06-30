package com.filnik.goosegamekata

import com.filnik.goosegamekata.model.Player
import com.filnik.goosegamekata.viewmodel.MainViewModel
import io.kotest.matchers.shouldBe
import org.junit.Test

class MainViewModelTest {
    private val viewModel = MainViewModel()

    @Test
    fun `SHOULD add player`() {
        viewModel.addPlayer("Pippo")

        viewModel.players.value shouldBe listOf(Player(0, "Pippo", 0, true))
    }
}
