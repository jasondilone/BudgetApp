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
import com.example.budgetapp.theme.BudgetAppTheme
//import com.example.com.example.budgetapp.BudgetAppTheme
import java.text.DecimalFormat

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            BudgetAppTheme(darkTheme = false) {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    BudgetApp()
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
val centsNumberFormatter = DecimalFormat("#,###.00")
val noCentsNumberFormatter = DecimalFormat("#,###")

// Roundness for surfaces
val roundDp: Dp = 8.dp

// Spent | Budget
var spent = 128.50
var budget = 1000

@Composable
fun BudgetApp() {
    var isDarkMode by remember { mutableStateOf(false) }

    Scaffold(
        bottomBar = {
            Box (
                modifier = Modifier.padding(horizontal = 20.dp)
                    .padding(top = 8.dp, bottom = 32.dp)
            ) {
                NavigationBar()
            }
        }
    ) { innerPadding ->

        // Test different screens until navigation is set up

        Home(modifier = Modifier.padding(innerPadding))
        //Expenses(modifier = Modifier.padding(innerPadding))
        //Add(modifier = Modifier.padding(innerPadding))
        //SetBudget(modifier = Modifier.padding(innerPadding))
        //Recurring(modifier = Modifier.padding(innerPadding))

        /*
        Settings(
            modifier = Modifier.padding(innerPadding),
            isDarkMode = isDarkMode,
            onDarkModeChange = { isDarkMode = it }
        )
         */
    }
}

@Preview(showBackground = true)
@Composable
fun BudgetAppPreview() {
    BudgetAppTheme(darkTheme = false) {
        BudgetApp()
    }
}