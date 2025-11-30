package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.theme.BudgetAppTheme
import java.time.LocalDate
import java.time.format.DateTimeFormatter

@Composable
fun ExpenseCard(expense: Expense) {

    Surface(
        modifier = Modifier.fillMaxWidth().padding(horizontal = 0.dp),
        color = MaterialTheme.colorScheme.primary,
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
                .height(IntrinsicSize.Min)
                .height(90.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {

            // Category and data Column
            Column(
                modifier = Modifier.weight(1f)
                    .padding(horizontal = 25.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = expense.category,
                    fontSize = smallFontSize,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "" + expense.date.format(DateTimeFormatter.ofPattern("MMM d")),
                    fontSize = mediumFontSize,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Amount Column
            Column(
                modifier = Modifier.weight(1f).fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = expense.description,
                    fontSize = smallFontSize,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.tertiary,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$" + centsNumberFormatter.format(expense.amount),
                    fontSize = largeFontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpenseCardPreview() {
    BudgetAppTheme(darkTheme = true) {
        // Example expense
        val myExpense = Expense(
            "Subscriptions 🔁",
            "Hulu",
            15.10,
            LocalDate.now(),
            true
        )
        ExpenseCard(myExpense)
    }
}