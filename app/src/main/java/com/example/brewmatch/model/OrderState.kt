package com.example.brewmatch.model

import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.runtime.MutableState

data class Drink(
    val name: String,
    val emoji: String,
    val basePrice: Int,
    val description: String = ""
)

class OrderState {
    var drinkName: MutableState<String> = mutableStateOf("Iced Caramel Latte")
    var basePrice: MutableState<Int> = mutableStateOf(110)
    var description: MutableState<String> = mutableStateOf("")

    var size: MutableState<String> = mutableStateOf("Medium")
    var sizeDelta: MutableState<Int> = mutableStateOf(0)

    var milk: MutableState<String> = mutableStateOf("No milk")
    var milkDelta: MutableState<Int> = mutableStateOf(0)

    var syrup: MutableState<String> = mutableStateOf("Caramel")
    var syrupDelta: MutableState<Int> = mutableStateOf(0)

    var sugarLevel: MutableState<Int> = mutableStateOf(50)

    val totalPrice: Int
        get() = basePrice.value + sizeDelta.value + milkDelta.value + syrupDelta.value
}