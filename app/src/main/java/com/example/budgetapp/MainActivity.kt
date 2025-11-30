package com.example.budgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
<<<<<<< Updated upstream
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.sp
import com.example.budgetapp.ui.theme.BudgetAppTheme
import java.time.LocalDate
import com.example.budgetapp.ui.Home

=======
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Receipt
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.wear.compose.navigation.currentBackStackEntryAsState
import com.example.budgetapp.theme.BudgetAppTheme
//import com.example.com.example.budgetapp.BudgetAppTheme
import java.text.DecimalFormat
>>>>>>> Stashed changes

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            BudgetAppTheme {
                Surface(color = MaterialTheme.colorScheme.background) {
                    Home()
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


<<<<<<< Updated upstream
//Helpers
fun autoFontSize(text: String): TextUnit {
    return when {
        text.length <= 8 -> 30.sp
        text.length <= 10 -> 25.sp
        text.length <= 12 -> 20.sp
        else -> 15.sp
    }
}
=======
// Spent | Budget
var spent = 128.50
var budget = 1000

@Composable
fun BudgetApp() {
    var isDarkMode by remember { mutableStateOf(false) }
    val navController = rememberNavController()

    Scaffold(
        bottomBar = {
            Box(
                modifier = Modifier
                    .padding(horizontal = 20.dp)
                    .padding(top = 8.dp, bottom = 32.dp)
            ) {
                NavigationBar(navController)
            }
        }
    ) { innerPadding ->

        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier.padding()
        ) {
            composable("home") {
                Home(
                    modifier = Modifier.padding(innerPadding),
                    spent = spent,
                    budget = budget
                )
            }
            composable("expenses") {
                Expenses(modifier = Modifier.padding(innerPadding))
            }
            composable("add") {
                Add(modifier = Modifier.padding(innerPadding))
            }
            composable("settings") {
                Settings(
                    modifier = Modifier.padding(innerPadding),
                    isDarkMode = isDarkMode,
                    onDarkModeChange = { isDarkMode = it }
                )
            }
        }
    }
}




@Preview(showBackground = true)
@Composable
fun BudgetAppPreview() {
    BudgetAppTheme(darkTheme = false) {
        BudgetApp()
    }
}
>>>>>>> Stashed changes
