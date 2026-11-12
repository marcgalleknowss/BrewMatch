package com.example.brewmatch.navigation

sealed class Screen(val route: String) {
    object Home : Screen("home")
    object Quiz : Screen("quiz")
    object Result : Screen("result")
    object Customize : Screen("customize")
    object Summary : Screen("summary")
    object Saved : Screen("saved")
}