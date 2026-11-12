package com.example.brewmatch

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.brewmatch.navigation.BrewMatchNavGraph
import com.example.brewmatch.ui.theme.BrewMatchTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BrewMatchTheme {
                Surface(modifier = Modifier.fillMaxSize()) {
                    BrewMatchNavGraph()
                }
            }
        }
    }
}