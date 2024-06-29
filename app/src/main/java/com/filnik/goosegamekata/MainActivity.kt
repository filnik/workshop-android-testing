package com.filnik.goosegamekata

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.filnik.goosegamekata.ui.theme.GooseGameKataTheme
import com.filnik.goosegamekata.view.GameBoardScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            GooseGameKataTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    GameBoardScreen(modifier = Modifier.padding(innerPadding))
                }
            }
        }
    }
}
