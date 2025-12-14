package com.example.budgetapp.ui

import android.graphics.drawable.Icon
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Repeat
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.smallFontSize
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.theme.color4
import com.example.budgetapp.theme.color8
import com.example.budgetapp.utility.formatCents
import com.example.budgetapp.utility.formatDate

data class CategoryUi(
    val name: String,
    val color: Color
)

@Composable
fun ExpenseCard(
    expense: Expense,
    category: CategoryUi,
    modifier: Modifier = Modifier
) {
    val dateText = formatDate(expense.dateEpochMillis)

    Surface(
        modifier = modifier.fillMaxWidth().padding(horizontal = 0.dp),
        color = MaterialTheme.colorScheme.primary,
        border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .height(90.dp),
            horizontalArrangement = Arrangement.SpaceEvenly,
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Category + date
            Column(
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 25.dp)
                    .align(Alignment.CenterVertically)
            ) {
                Text(
                    text = category.name,
                    fontSize = smallFontSize,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier.fillMaxWidth(),
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = dateText,
                    fontSize = mediumFontSize,
                    maxLines = 1,
                    color = MaterialTheme.colorScheme.secondary
                )
            }

            // Description + amount
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                // isRecurring icon added to description if true
                Row(

                ) {
                    Text(
                        text = expense.description,
                        fontSize = smallFontSize,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.tertiary,
                        fontWeight = FontWeight.Bold
                    )
                    if (expense.isRecurring) {
                        Icon(
                            imageVector = Icons.Default.Repeat,
                            contentDescription = "Recurring",
                            tint = MaterialTheme.colorScheme.tertiary,
                            modifier = Modifier.padding(start = 10.dp)
                        )
                    }
                }

                Text(
                    text = formatCents(expense.amountCents),
                    fontSize = largeFontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1
                )
            }
            Surface(
                modifier = Modifier
                    .fillMaxHeight()
                    .width(25.dp),
                color = category.color,
                content = {}
            )
        }
    }
}

@Preview
@Composable
fun ExpenseCardPreview() {
    BudgetAppTheme(darkTheme = false) {
        val myExpense = Expense(
            id = 1,
            categoryId = 1,
            description = "trader joes",
            amountCents = 1539,
            dateEpochMillis = System.currentTimeMillis(),
            isRecurring = true
        )
        ExpenseCard(
            expense = myExpense,
            category = CategoryUi(
                name = "groceries",
                color = color4
            )
        )
    }
}