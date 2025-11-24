package com.example.budgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.budgetapp.ui.theme.BudgetAppTheme
import java.time.LocalDate
import com.example.budgetapp.ui.Home


class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    //Home()
                    AddScreen()
                }
            }
        }
    }
}
// Data
data class Expense(
    val category: String,
    val amount: Double,
    val date: LocalDate
)

// Sample data
val sampleExpense = Expense("Shopping 🛍", 1888.80, LocalDate.now())
val expenses = mutableListOf(
    Expense("Food 🍔", 23.15, LocalDate.now()),
    Expense("Bills 🧾", 233.98, LocalDate.now()),
    Expense("Food 🍔", 13.29, LocalDate.now()),
    Expense("Shopping 🛍", 48.03, LocalDate.now()),
    Expense("Subscriptions 🔁", 14.99, LocalDate.now()),
    Expense("Bills 🧾", 69.95, LocalDate.now())
)

// Globals
val spent: Double = 374.70
val budget: Double = 1000.00


//Helpers
fun autoFontSize(text: String): TextUnit {
    return when {
        text.length <= 8 -> 30.sp
        text.length <= 10 -> 25.sp
        text.length <= 12 -> 20.sp
        else -> 15.sp
    }
}
