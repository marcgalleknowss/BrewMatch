package com.example.brewmatch.ui.customize

import androidx.compose.material3.IconButton
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
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

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun CustomizeScreen(navController: NavController, orderState: OrderState) {

    val drinks = listOf(
        Triple("Iced Caramel Latte", 110, 0),
        Triple("Hot Americano", 90, 0),
        Triple("Matcha Latte", 120, 0),
        Triple("Cold Brew", 115, 0)
    )

    val sizes = listOf(
        Triple("Small", -10, 0),
        Triple("Medium", 0, 1),
        Triple("Large", 20, 2)
    )

    val milkOptions = listOf(
        Pair("Whole Milk", 0),
        Pair("Oat milk +₱15", 15),
        Pair("Almond milk +₱15", 15),
        Pair("No milk", 0)
    )

    val syrupOptions = listOf(
        Pair("Caramel", 0),
        Pair("Vanilla +₱10", 10),
        Pair("Hazelnut +₱10", 10),
        Pair("None", 0)
    )

    var selectedDrink by remember { mutableStateOf(orderState.drinkName.value) }
    var selectedSizeIndex by remember { mutableIntStateOf(1) }
    var selectedMilk by remember { mutableStateOf(orderState.milk.value) }
    var selectedSyrup by remember { mutableStateOf(orderState.syrup.value) }
    var sugarLevel by remember { mutableFloatStateOf(orderState.sugarLevel.value.toFloat()) }

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(
                        text = "Customize your order",
                        style = MaterialTheme.typography.titleLarge,
                        color = BrewBlack
                    )
                },
                navigationIcon = {
                    IconButton(onClick = { navController.popBackStack() }) {
                        Text(
                            text = "←",
                            style = MaterialTheme.typography.titleLarge,
                            color = BrewBlack
                        )
                    }
                },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = BrewWhite
                )
            )
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .background(BrewWhite)
                    .padding(horizontal = 24.dp, vertical = 16.dp)
            ) {
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

                Spacer(modifier = Modifier.height(12.dp))

                Button(
                    onClick = { navController.navigate(Screen.Summary.route) },
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
                        text = "Review order",
                        style = MaterialTheme.typography.titleLarge
                    )
                }

                Spacer(modifier = Modifier.height(8.dp))

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
    ) { paddingValues ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
                .padding(horizontal = 24.dp, vertical = 16.dp)
        ) {
            // DRINK
            SectionLabel(text = "DRINK")
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                drinks.forEach { (name, price, _) ->
                    OptionChip(
                        text = name,
                        selected = selectedDrink == name,
                        onClick = {
                            selectedDrink = name
                            orderState.drinkName.value = name
                            orderState.basePrice.value = price
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SIZE — SegmentedButton
            SectionLabel(text = "SIZE")
            SingleChoiceSegmentedButtonRow(
                modifier = Modifier.fillMaxWidth()
            ) {
                sizes.forEachIndexed { index, (label, delta, _) ->
                    SegmentedButton(
                        selected = selectedSizeIndex == index,
                        onClick = {
                            selectedSizeIndex = index
                            orderState.size.value = label
                            orderState.sizeDelta.value = delta
                        },
                        shape = SegmentedButtonDefaults.itemShape(
                            index = index,
                            count = sizes.size
                        ),
                        colors = SegmentedButtonDefaults.colors(
                            activeContainerColor = BrewBrown,
                            activeContentColor = BrewWhite,
                            inactiveContainerColor = BrewWhite,
                            inactiveContentColor = BrewBlack
                        )
                    ) {
                        Text(
                            text = label,
                            style = MaterialTheme.typography.bodyLarge
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // MILK — FlowRow chips
            SectionLabel(text = "MILK")
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                milkOptions.forEach { (label, delta) ->
                    OptionChip(
                        text = label,
                        selected = selectedMilk == label,
                        onClick = {
                            selectedMilk = label
                            orderState.milk.value = label
                            orderState.milkDelta.value = delta
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SYRUP — FlowRow chips
            SectionLabel(text = "SYRUP ADD-ON")
            FlowRow(
                horizontalArrangement = Arrangement.spacedBy(8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp),
                modifier = Modifier.fillMaxWidth()
            ) {
                syrupOptions.forEach { (label, delta) ->
                    OptionChip(
                        text = label,
                        selected = selectedSyrup == label,
                        onClick = {
                            selectedSyrup = label
                            orderState.syrup.value = label
                            orderState.syrupDelta.value = delta
                        }
                    )
                }
            }

            Spacer(modifier = Modifier.height(20.dp))

            // SUGAR LEVEL — Slider
            SectionLabel(text = "SUGAR LEVEL")
            Slider(
                value = sugarLevel,
                onValueChange = {
                    sugarLevel = it
                    orderState.sugarLevel.value = it.toInt()
                },
                valueRange = 0f..100f,
                steps = 3,
                modifier = Modifier.fillMaxWidth(),
                colors = SliderDefaults.colors(
                    thumbColor = BrewBrown,
                    activeTrackColor = BrewBrown,
                    inactiveTrackColor = BrewGray.copy(alpha = 0.3f)
                )
            )
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                listOf("0%", "25%", "50%", "75%", "100%").forEach { label ->
                    Text(
                        text = label,
                        style = MaterialTheme.typography.bodyMedium,
                        color = BrewGray,
                        fontSize = 11.sp
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))
        }
    }
}

@Composable
fun SectionLabel(text: String) {
    Text(
        text = text,
        fontFamily = PatrickHandSC,
        fontSize = 13.sp,
        color = BrewBrown,
        letterSpacing = 1.sp,
        modifier = Modifier.padding(bottom = 10.dp)
    )
}

@Composable
fun OptionChip(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .clip(RoundedCornerShape(20.dp))
            .background(if (selected) BrewBrown else BrewWhite)
            .border(
                width = if (selected) 0.dp else 1.dp,
                color = BrewBlack,
                shape = RoundedCornerShape(20.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 8.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected) BrewWhite else BrewBlack
        )
    }
}