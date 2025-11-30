package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.theme.BudgetAppTheme
import java.time.LocalDate

@Composable
fun Expenses(modifier: Modifier = Modifier) {
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedTime by remember { mutableStateOf(false) }

    // Sample Data
    val expenses = listOf<Expense>(
        Expense("Food",
            "",
            26.02,
            LocalDate.now(),
            false
        ),
        Expense(
            "Subscription",
            "YouTube Premium",
            11.10,
            LocalDate.now(),
            true
        ),
        Expense("Shopping",
            "Target",
            39.99,
            LocalDate.now(),
            false
        ),
        Expense("Shopping",
            "Amazon",
            67.23,
            LocalDate.now(),
            false
        ),
        Expense("Food",
            "Restaurant",
            80.17,
            LocalDate.now(),
            false
        ),
        Expense("Bills",
            "insurance",
            193.88,
            LocalDate.now(),
            true
        ),
        Expense("Subscription",
            "Spotify",
            15.00,
            LocalDate.now(),
            true
        ),
    )

    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Expenses
            Text(
                text = stringResource(R.string.expenses),
                fontSize = largeFontSize,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))

            // Clear all
            Box(
                modifier = Modifier
                    .clickable {
                        // "Clear all" logic here
                    }
            ) {
                Text(
                    text = stringResource(R.string.clear_all),
                    fontSize = smallFontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    modifier = Modifier
                        .padding(
                            start = 40.dp,
                            end = 40.dp,
                            top = 5.dp,
                            bottom = 5.dp
                        )
                )
            }
        }

        // Category selection
        Surface(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.primary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier.padding(0.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Selection Column
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.all_categories),
                            fontSize = mediumFontSize,
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                // Dropdown
                IconButton(
                    onClick = { expandedCategory = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = expandedCategory,
                    onDismissRequest = { expandedCategory = false },
                    modifier = Modifier
                        .width(330.dp)
                        .border(2.dp, Color.Black, RoundedCornerShape(roundDp))
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp))
                ) {

                }
            }
        }

        // Time selection
        Surface(
            modifier = Modifier.fillMaxWidth().padding(10.dp)
                .padding(bottom = 10.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.primary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier.padding(0.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly,
                verticalAlignment = Alignment.CenterVertically
            ) {

                Row(
                    modifier = Modifier.weight(1f),
                    verticalAlignment = Alignment.CenterVertically
                ) {

                    // Selection Column
                    Column(
                        modifier = Modifier.weight(1f),
                        horizontalAlignment = Alignment.CenterHorizontally
                    ) {
                        Text(
                            text = stringResource(R.string.this_month),
                            fontSize = mediumFontSize,
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }
                // Dropdown
                IconButton(
                    onClick = { expandedTime = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }

                DropdownMenu(
                    expanded = expandedTime,
                    onDismissRequest = { expandedTime = false },
                    modifier = Modifier
                        .width(330.dp)
                        .border(2.dp, Color.Black, RoundedCornerShape(roundDp))
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp))
                ) {

                }
            }
        }

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
fun ExpensesPreview() {
    BudgetAppTheme(false) {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = 8.dp, bottom = 32.dp)
                ) {
                    NavigationBar()
                }
            }
        ) { innerPadding ->
            Expenses(modifier = Modifier.padding(innerPadding))
        }
    }
}