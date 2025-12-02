package com.example.budgetapp

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.theme.BudgetAppTheme
import java.time.LocalDate

@Composable
fun Recurring(modifier: Modifier = Modifier) {
    val expenses = listOf<Expense>(
        Expense("Subscription",
            "DoorDash",
            10.99,
            LocalDate.now(),
            true
        ),
        Expense("Subscription",
            "Disney+",
            5.99,
            LocalDate.now(),
            true
        ),
        Expense("Bills",
            "T-Mobile",
            66.89,
            LocalDate.now(),
            true
        )
    )

    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.rotate(90f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp)
                    .clickable { }
            )
            Text(
                text = stringResource(R.string.recurring_expenses),
                fontSize = largeFontSize
            )
        }
        Spacer(modifier = Modifier.padding(10.dp))
        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(expenses) { expense ->
                ExpenseCard(expense = expense)
            }
        }
    }
}

@Preview
@Composable
fun RecurringPreview() {
    BudgetAppTheme(false) {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = 8.dp, bottom = 32.dp)
                ) {
                    NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Recurring(modifier = Modifier.padding(innerPadding))
        }
    }
}