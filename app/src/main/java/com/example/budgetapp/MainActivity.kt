package com.example.budgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.ui.Add
import com.example.budgetapp.ui.Expenses
import com.example.budgetapp.ui.Home
import com.example.budgetapp.ui.NavigationBar
import com.example.budgetapp.ui.Settings
//import com.example.com.example.budgetapp.BudgetAppTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            var isDarkMode by remember { mutableStateOf(false) }
            BudgetAppTheme(darkTheme = isDarkMode) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BudgetApp(
                        isDarkMode,
                        onThemeChange = { isDarkMode = it }
                    )
                }
            }
        }
    }
}

// Font Sizes
val largeFontSize = 35.sp
val mediumFontSize = 25.sp
val smallFontSize = 15.sp

// Formatters
val centsNumberFormatter = DecimalFormat("#,##0.00")
val noCentsNumberFormatter = DecimalFormat("#,###")

// Roundness for surfaces
val roundDp: Dp = 8.dp

/* Spent | Budget
var spent = 336.25
var budget = 1000
*/

@Composable
fun BudgetApp(isDarkMode: Boolean, onThemeChange: (Boolean) -> Unit) {
    //var isDarkMode by remember { mutableStateOf(false) }
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
                    modifier = Modifier.padding(innerPadding)
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
                    onToggleDarkMode = onThemeChange
                )
            }
        }
    }
}
@Preview(showBackground = true)
@Composable
fun BudgetAppPreview() {
    BudgetAppTheme() {
        BudgetApp(
            false,
            onThemeChange = {  }
            )
    }
}