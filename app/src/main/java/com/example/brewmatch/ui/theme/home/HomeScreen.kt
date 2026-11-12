package com.example.brewmatch.ui.home

import androidx.compose.foundation.layout.*
import com.example.brewmatch.ui.theme.Pattaya
import androidx.compose.ui.unit.sp
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.brewmatch.navigation.Screen
import com.example.brewmatch.ui.theme.BrewBlack
import com.example.brewmatch.ui.theme.BrewBrown
import com.example.brewmatch.ui.theme.PatrickHand

@Composable
fun HomeScreen(navController: NavController) {
    Box(
        modifier = Modifier.fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.Center)
                .padding(horizontal = 24.dp)
                .offset(y = (-60).dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Title
            BrewMatchTitle()

            Spacer(modifier = Modifier.height(5.dp))

            // Subtitle
            Text(
                text = "Your personal coffee companion.\nFind your perfect cup, your way.",
                fontFamily = PatrickHand,
                style = MaterialTheme.typography.bodyLarge,
                textAlign = TextAlign.Center,
                color = BrewBlack
            )

            Spacer(modifier = Modifier.height(40.dp))

            // Help me decide button
            OutlinedButton(
                onClick = { navController.navigate(Screen.Quiz.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = BrewBlack
                )
            ) {
                Text(
                    text = "◇ Help me decide",
                    fontFamily = PatrickHand,
                    style = MaterialTheme.typography.titleLarge
                )
            }

            Spacer(modifier = Modifier.height(12.dp))

            // I know what I want button
            OutlinedButton(
                onClick = { navController.navigate(Screen.Customize.route) },
                modifier = Modifier
                    .fillMaxWidth()
                    .height(52.dp),
                shape = RoundedCornerShape(12.dp),
                colors = ButtonDefaults.outlinedButtonColors(
                    contentColor = BrewBlack
                )
            ) {
                Text(
                    text = "I know what I want",
                    fontFamily = PatrickHand,
                    style = MaterialTheme.typography.titleLarge
                )
            }
        }
    }
}

@Composable
fun BrewMatchTitle() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        Text(
            text = "Brew",
            fontFamily = Pattaya,
            fontSize = 58.sp,
            color = BrewBrown
        )
        Text(
            text = "Match",
            fontFamily = Pattaya,
            fontSize = 58.sp,
            color = BrewBlack
        )
    }
}