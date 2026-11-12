package com.example.brewmatch.navigation

import androidx.compose.runtime.remember
import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.brewmatch.model.OrderState
import com.example.brewmatch.ui.home.HomeScreen
import com.example.brewmatch.ui.quiz.QuizScreen
import com.example.brewmatch.ui.result.ResultScreen
import com.example.brewmatch.ui.customize.CustomizeScreen
import com.example.brewmatch.ui.summary.SummaryScreen
import com.example.brewmatch.ui.summary.SavedScreen

@Composable
fun BrewMatchNavGraph() {
    val navController = rememberNavController()
    val orderState = remember { OrderState() }

    NavHost(
        navController = navController,
        startDestination = Screen.Home.route
    ) {
        composable(Screen.Home.route) {
            HomeScreen(navController = navController)
        }
        composable(Screen.Quiz.route) {
            QuizScreen(navController = navController, orderState = orderState)
        }
        composable(Screen.Result.route) {
            ResultScreen(navController = navController, orderState = orderState)
        }
        composable(Screen.Customize.route) {
            CustomizeScreen(navController = navController, orderState = orderState)
        }
        composable(Screen.Summary.route) {
            SummaryScreen(navController = navController, orderState = orderState)
        }
        composable(Screen.Saved.route) {
            SavedScreen(navController = navController, orderState = orderState)
        }
    }
}