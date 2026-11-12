package com.example.brewmatch.ui.result

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.brewmatch.model.OrderState
import com.example.brewmatch.navigation.Screen
import com.example.brewmatch.ui.theme.BrewBlack
import com.example.brewmatch.ui.theme.BrewBrown
import com.example.brewmatch.ui.theme.BrewGray
import com.example.brewmatch.ui.theme.BrewWhite
import com.example.brewmatch.ui.theme.PatrickHandSC

@Composable
fun ResultScreen(navController: NavController, orderState: OrderState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        // Top bar
        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "← ",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBlack,
                modifier = Modifier.clickable { navController.popBackStack() }
            )
            Text(
                text = "Mood Matcher",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBlack
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Center content
        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            // Flower icon
            Text(
                text = "✿",
                fontSize = 48.sp,
                color = BrewBrown
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Match label
            Text(
                text = "Perfect match for your mood",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBrown,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(16.dp))

            // Drink name
            Text(
                text = orderState.drinkName.value,
                fontFamily = PatrickHandSC,
                fontSize = 28.sp,
                color = BrewBrown,
                textAlign = TextAlign.Center
            )

            Spacer(modifier = Modifier.height(12.dp))

            // Description
            Text(
                text = orderState.description.value,
                style = MaterialTheme.typography.bodyMedium,
                color = BrewGray,
                textAlign = TextAlign.Center,
                lineHeight = 22.sp
            )

            Spacer(modifier = Modifier.height(32.dp))

            // Price row
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Column {
                    Text(
                        text = "BASE PRICE STARTS AT",
                        fontFamily = PatrickHandSC,
                        fontSize = 11.sp,
                        color = BrewBrown,
                        letterSpacing = 1.sp
                    )
                    Text(
                        text = "TOTAL PRICE",
                        fontFamily = PatrickHandSC,
                        fontSize = 11.sp,
                        color = BrewBrown,
                        letterSpacing = 1.sp
                    )
                }
                Text(
                    text = "₱${orderState.basePrice.value}",
                    fontFamily = PatrickHandSC,
                    fontSize = 36.sp,
                    color = BrewBlack
                )
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons
        Button(
            onClick = { navController.navigate(Screen.Customize.route) },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrewBrown,
                contentColor = BrewWhite
            )
        ) {
            Text(
                text = "Customize this drink",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        OutlinedButton(
            onClick = {
                navController.navigate(Screen.Home.route) {
                    popUpTo(Screen.Home.route) { inclusive = true }
                }
            },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BrewBlack
            )
        ) {
            Text(
                text = "Start over",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}