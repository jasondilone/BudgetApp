package com.example.budgetapp.ui

import androidx.compose.foundation.BorderStroke
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.R
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.roundDp
import com.example.budgetapp.smallFontSize
import com.example.budgetapp.theme.BudgetAppTheme

@Composable
fun Expenses(modifier: Modifier = Modifier) {
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedTime by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf("All") }
    var selectedTime by remember { mutableStateOf("This Month") }
    var expenses = listOf<Expense>(
        // Don't need sample data, use real data
    )

    /*
    val filteredExpenses = expenses.filter { expense ->
        val categoryMatch = (selectedCategory == "All" || expense.categoryId.toString() == selectedCategory)

        val timeMatch = when (selectedTime) {
            "This Month" -> expense.dateEpochMillis.month == LocalDate.now().month &&
                    expense.date.year == LocalDate.now().year

            "This Week" -> {
                val now = LocalDate.now()
                val startOfWeek = now.with(DayOfWeek.MONDAY)
                val endOfWeek = now.with(DayOfWeek.SUNDAY)
                !expense.date.isBefore(startOfWeek) && !expense.date.isAfter(endOfWeek)
            }
            else -> true // "All Time"
        }
        categoryMatch && timeMatch
    }
*/
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

        // sample categories
        val categories = listOf("All", "Food", "Transportation", "Entertainment", "Other")
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
                            text = selectedCategory,
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
                        .width(370.dp)
                        .border(0.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(roundDp)),
                    containerColor = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(roundDp)
                ) {
                    categories.forEach { category ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = category,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    fontSize = mediumFontSize,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            onClick = {
                                selectedCategory = category
                                expandedCategory = false
                            }
                        )
                    }
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
                            text = selectedTime,
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

                /*
                DropdownMenu(
                    expanded = expandedTime,
                    onDismissRequest = { expandedTime = false },
                    modifier = Modifier
                        .width(330.dp)
                        .border(0.dp, Color.Black, RoundedCornerShape(roundDp))
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp))
                ) {
                    //ALL CATEGORIES DROPDOWN LIST
                    //val categories = listOf("All", "Food", "Shopping", "Bills", "Subscription")
                    /*
                    DropdownMenu(
                        expanded = expandedCategory,
                        onDismissRequest = { expandedCategory = false }
                    ) {
                        categories.forEach { category ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        category,
                                        color = MaterialTheme.colorScheme.onPrimary
                                    )
                                },
                                onClick = {
                                    selectedCategory = category
                                    expandedCategory = false
                                }
                            )
                        }
                    }
                    */


                 */
                    //TIME SPAN DROPDOWN LIST
                    val timeSpans = listOf("This Month", "This Week", "All Time")

                    DropdownMenu(
                        expanded = expandedTime,
                        onDismissRequest = { expandedTime = false },
                        modifier = Modifier
                            .width(370.dp)
                            .border(0.dp, MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp)),
                        containerColor = MaterialTheme.colorScheme.secondary,
                        shape = RoundedCornerShape(roundDp)
                    ) {
                        timeSpans.forEach { span ->
                            DropdownMenuItem(
                                text = {
                                    Text(
                                        span,
                                        color = MaterialTheme.colorScheme.onTertiary,
                                        fontSize = mediumFontSize,
                                        modifier = Modifier.fillMaxWidth(),
                                        textAlign = TextAlign.Center
                                    )
                                       },
                                onClick = {
                                    selectedTime = span
                                    expandedTime = false
                                }
                            )
                        }
                    }
            }
        }

        LazyColumn(
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(0) { expense ->
                //ExpenseCard(expense = expense)
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
                    //NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Expenses(modifier = Modifier.padding(innerPadding))
        }
    }
}