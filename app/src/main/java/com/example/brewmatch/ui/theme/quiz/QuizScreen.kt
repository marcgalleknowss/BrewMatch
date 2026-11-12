package com.example.brewmatch.ui.quiz

import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.togetherWith
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.example.brewmatch.model.OrderState
import com.example.brewmatch.navigation.Screen
import com.example.brewmatch.ui.theme.BrewBlack
import com.example.brewmatch.ui.theme.BrewBrown
import com.example.brewmatch.ui.theme.BrewWhite
import com.example.brewmatch.ui.theme.PatrickHandSC

data class QuizQuestion(
    val text: String,
    val options: List<Pair<String, String>>
)

val quizQuestions = listOf(
    QuizQuestion(
        text = "How's your energy level right now?",
        options = listOf(
            "low" to "Pretty low — I need a boost",
            "mid" to "Moderate — just keeping steady",
            "high" to "Already good — something light"
        )
    ),
    QuizQuestion(
        text = "What flavor mood are you in?",
        options = listOf(
            "sweet" to "Sweet & comforting",
            "bold" to "Bold & rich coffee flavor",
            "fruity" to "Light & fruity / refreshing"
        )
    ),
    QuizQuestion(
        text = "Hot or cold?",
        options = listOf(
            "hot" to "Hot — warm me up",
            "iced" to "Iced — cool me down",
            "any" to "Surprise me"
        )
    ),
    QuizQuestion(
        text = "Any dietary preference?",
        options = listOf(
            "none" to "No preference — anything works",
            "nondairy" to "Non-dairy milk please",
            "nosugar" to "Low sugar / healthy option"
        )
    )
)

@Composable
fun QuizScreen(navController: NavController, orderState: OrderState) {
    var currentQuestion by remember { mutableIntStateOf(0) }
    val answers = remember { mutableStateMapOf<Int, String>() }

    val question = quizQuestions[currentQuestion]
    val progress = (currentQuestion + 1) / quizQuestions.size.toFloat()
    val selectedKey = answers[currentQuestion]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 24.dp)
    ) {
        Spacer(modifier = Modifier.height(48.dp))

        Row(verticalAlignment = Alignment.CenterVertically) {
            Text(
                text = "← ",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBlack,
                modifier = Modifier.clickable {
                    if (currentQuestion == 0) {
                        navController.popBackStack()
                    } else {
                        currentQuestion--
                    }
                }
            )
            Text(
                text = "Mood Matcher",
                style = MaterialTheme.typography.titleLarge,
                color = BrewBlack
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        LinearProgressIndicator(
            progress = { progress },
            modifier = Modifier
                .fillMaxWidth()
                .height(6.dp)
                .clip(RoundedCornerShape(3.dp)),
            color = BrewBrown,
            trackColor = Color(0xFFE0E0E0)
        )

        Spacer(modifier = Modifier.height(24.dp))

        AnimatedContent(
            targetState = currentQuestion,
            transitionSpec = {
                (slideInHorizontally { width -> width } + fadeIn()) togetherWith
                        (slideOutHorizontally { width -> -width } + fadeOut())
            },
            label = "quiz_question"
        ) { qIndex ->
            val q = quizQuestions[qIndex]
            Column {
                Text(
                    text = "Question ${qIndex + 1} of ${quizQuestions.size}",
                    style = MaterialTheme.typography.labelLarge,
                    color = BrewBrown
                )

                Spacer(modifier = Modifier.height(8.dp))

                Text(
                    text = q.text,
                    style = MaterialTheme.typography.headlineMedium,
                    fontFamily = PatrickHandSC,
                    color = BrewBlack
                )

                Spacer(modifier = Modifier.height(20.dp))

                q.options.forEach { (key, label) ->
                    val isSelected = answers[qIndex] == key
                    QuizOptionCard(
                        text = label,
                        selected = isSelected,
                        onClick = { answers[qIndex] = key }
                    )
                    Spacer(modifier = Modifier.height(10.dp))
                }
            }
        }

        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = {
                if (currentQuestion < quizQuestions.size - 1) {
                    currentQuestion++
                } else {
                    applyQuizResult(answers, orderState)
                    navController.navigate(Screen.Result.route)
                }
            },
            enabled = selectedKey != null,
            modifier = Modifier
                .fillMaxWidth()
                .height(52.dp),
            shape = RoundedCornerShape(12.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = BrewBrown,
                contentColor = BrewWhite,
                disabledContainerColor = BrewBrown.copy(alpha = 0.4f),
                disabledContentColor = BrewWhite
            )
        ) {
            Text(
                text = if (currentQuestion < quizQuestions.size - 1) "Next" else "Find my drink",
                style = MaterialTheme.typography.titleLarge
            )
        }

        Spacer(modifier = Modifier.height(24.dp))
    }
}

@Composable
fun QuizOptionCard(text: String, selected: Boolean, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(12.dp))
            .background(if (selected) BrewBrown else BrewWhite)
            .border(
                width = if (selected) 0.dp else 1.dp,
                color = BrewBlack,
                shape = RoundedCornerShape(12.dp)
            )
            .clickable { onClick() }
            .padding(horizontal = 16.dp, vertical = 14.dp)
    ) {
        Text(
            text = text,
            style = MaterialTheme.typography.bodyLarge,
            color = if (selected) BrewWhite else BrewBlack
        )
    }
}

fun applyQuizResult(answers: Map<Int, String>, orderState: OrderState) {
    val flavor = answers[1] // Question 2 = flavor
    val temp = answers[2]   // Question 3 = hot/cold

    val name: String
    val price: Int
    val desc: String

    when {
        flavor == "bold" && temp == "hot" -> {
            name = "Hot Americano"
            price = 90
            desc = "Pure, intense espresso diluted to perfection — clean and powerful for when you need to focus."
        }
        flavor == "bold" && temp == "iced" -> {
            name = "Cold Brew"
            price = 115
            desc = "Steeped for 12 hours for a smooth, low-acid coffee hit that keeps you going all afternoon."
        }
        flavor == "sweet" && temp == "hot" -> {
            name = "Caramel Macchiato"
            price = 115
            desc = "Layers of vanilla, steamed milk, and bold espresso topped with caramel drizzle. Pure comfort."
        }
        flavor == "sweet" && temp == "iced" -> {
            name = "Iced Caramel Latte"
            price = 110
            desc = "Rich espresso meets silky milk and sweet caramel — the perfect pick-me-up served chilled."
        }
        flavor == "fruity" && temp == "hot" -> {
            name = "Caramel Macchiato"
            price = 115
            desc = "Warm, layered, and aromatic — a cozy pick for those who like their coffee with a twist."
        }
        flavor == "fruity" && temp == "iced" -> {
            name = "Iced Lemon Soda"
            price = 95
            desc = "A zesty, bubbly refresher — citrusy and light, perfect when you want something bright and fun."
        }
        else -> {
            name = "Matcha Latte"
            price = 120
            desc = "Earthy, creamy, and gently energizing — a beautiful balance of calm focus."
        }
    }

    orderState.drinkName.value = name
    orderState.basePrice.value = price
    orderState.description.value = desc
    orderState.sizeDelta.value = 0
    orderState.size.value = "Medium"
    orderState.milkDelta.value = 0
    orderState.milk.value = "No milk"
    orderState.syrupDelta.value = 0
    orderState.syrup.value = "Caramel"
    orderState.sugarLevel.value = 50
}