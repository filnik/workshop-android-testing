package com.filnik.goosegamekata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.filnik.goosegamekata.ui.theme.GooseGameKataTheme
import com.filnik.goosegamekata.view.AddPlayerScreen
import com.filnik.goosegamekata.view.GameBoardScreen
import com.filnik.goosegamekata.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.serialization.Serializable

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val viewModel: MainViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GooseGameKataTheme {
                val navController = rememberNavController()
                NavHost(
                    navController = navController,
                    startDestination = AddPlayerScreenRoute,
                ) {
                    composable<AddPlayerScreenRoute> {
                        AddPlayerScreen(
                            {
                                navController.navigate(GameBoardScreenRoute)
                            },
                            viewModel.players,
                            viewModel.errorMessage,
                            viewModel::addPlayer,
                        )
                    }
                    composable<GameBoardScreenRoute> {
                        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                            GameBoardScreen(
                                modifier = Modifier.padding(innerPadding),
                                players = viewModel.players,
                                diceFlow = viewModel.dice,
                                rollDice = viewModel::rollDice,
                            )
                        }
                    }
                }
            }
        }
    }
}

@Serializable
object AddPlayerScreenRoute

@Serializable
object GameBoardScreenRoute
