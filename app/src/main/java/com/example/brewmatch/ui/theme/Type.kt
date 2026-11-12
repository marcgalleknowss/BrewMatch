package com.example.brewmatch.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.example.brewmatch.R

val PatrickHand = FontFamily(
    Font(R.font.patrick_hand_regular, FontWeight.Normal)
)

val PatrickHandSC = FontFamily(
    Font(R.font.patrick_hand_sc_regular, FontWeight.Normal)
)

val Pattaya = FontFamily(
    Font(R.font.pattaya_regular, FontWeight.Normal)
)

val BrewTypography = Typography(
    headlineLarge = TextStyle(
        fontFamily = PatrickHandSC,
        fontWeight = FontWeight.Normal,
        fontSize = 36.sp
    ),
    headlineMedium = TextStyle(
        fontFamily = PatrickHandSC,
        fontWeight = FontWeight.Normal,
        fontSize = 24.sp
    ),
    titleLarge = TextStyle(
        fontFamily = PatrickHand,
        fontWeight = FontWeight.Normal,
        fontSize = 18.sp
    ),
    bodyLarge = TextStyle(
        fontFamily = PatrickHand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    bodyMedium = TextStyle(
        fontFamily = PatrickHand,
        fontWeight = FontWeight.Normal,
        fontSize = 14.sp
    ),
    labelLarge = TextStyle(
        fontFamily = PatrickHand,
        fontWeight = FontWeight.Normal,
        fontSize = 16.sp
    ),
    labelMedium = TextStyle(
        fontFamily = PatrickHandSC,
        fontWeight = FontWeight.Normal,
        fontSize = 13.sp
    )
)