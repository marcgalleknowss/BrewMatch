package com.example.brewmatch.ui.summary

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
fun SummaryScreen(navController: NavController, orderState: OrderState) {
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
                text = "Order summary",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBlack
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // Drink name
        Text(
            text = orderState.drinkName.value,
            fontFamily = PatrickHandSC,
            fontSize = 28.sp,
            color = BrewBrown,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Summary rows
        SummaryRow(label = "Size", value = orderState.size.value)
        SummaryRow(label = "Milk", value = orderState.milk.value)
        SummaryRow(label = "Syrup", value = orderState.syrup.value)
        SummaryRow(label = "Sugar level", value = "${orderState.sugarLevel.value}%")

        Spacer(modifier = Modifier.height(16.dp))

        HorizontalDivider(color = BrewGray.copy(alpha = 0.3f))

        Spacer(modifier = Modifier.height(12.dp))

        // Total price
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "TOTAL PRICE",
                fontFamily = PatrickHandSC,
                fontSize = 12.sp,
                color = BrewBrown,
                letterSpacing = 1.sp
            )
            Text(
                text = "₱${orderState.totalPrice}",
                fontFamily = PatrickHandSC,
                fontSize = 32.sp,
                color = BrewBlack
            )
        }

        Spacer(modifier = Modifier.weight(1f))

        // Save as signature drink button
        Button(
            onClick = { navController.navigate(Screen.Saved.route) },
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
                text = "☆ Save as signature drink",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(12.dp))

        // Edit order button
        OutlinedButton(
            onClick = { navController.popBackStack() },
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.outlinedButtonColors(
                contentColor = BrewBlack
            )
        ) {
            Text(
                text = "Edit order",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun SummaryRow(label: String, value: String) {
    Column {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(vertical = 10.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = label,
                style = MaterialTheme.typography.bodyLarge,
                color = BrewBlack
            )
            Text(
                text = value,
                style = MaterialTheme.typography.bodyLarge,
                color = BrewGray
            )
        }
        HorizontalDivider(color = BrewGray.copy(alpha = 0.3f))
    }
}

@Composable
fun SavedScreen(navController: NavController, orderState: OrderState) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        // Flower icon
        Text(
            text = "✿",
            fontSize = 56.sp,
            color = BrewBrown
        )

        Spacer(modifier = Modifier.height(16.dp))

        // Title
        Text(
            text = "Signature Drink Saved!",
            fontFamily = PatrickHandSC,
            fontSize = 24.sp,
            color = BrewBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Subtitle
        Text(
            text = "Your order is ready. Show this screen\nto the barista when ordering.",
            style = MaterialTheme.typography.bodyLarge,
            color = BrewBlack,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(32.dp))

        // Drink name
        Text(
            text = orderState.drinkName.value,
            fontFamily = PatrickHandSC,
            fontSize = 24.sp,
            color = BrewBrown,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(8.dp))

        // Details
        Text(
            text = "${orderState.size.value} · ${orderState.milk.value} · ${orderState.syrup.value} syrup · ${orderState.sugarLevel.value}% sugar",
            style = MaterialTheme.typography.bodyMedium,
            color = BrewGray,
            textAlign = TextAlign.Center
        )

        Spacer(modifier = Modifier.height(12.dp))

        // Price
        Text(
            text = "₱${orderState.totalPrice}",
            fontFamily = PatrickHandSC,
            fontSize = 32.sp,
            color = BrewBlack
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Back to home button
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
                text = "Back to home",
                style = MaterialTheme.typography.titleLarge
            )
        }
    }
}