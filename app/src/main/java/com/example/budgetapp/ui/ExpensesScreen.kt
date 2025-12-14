package com.example.budgetapp.ui

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
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.R
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.roundDp
import com.example.budgetapp.smallFontSize
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.theme.color4
import com.example.budgetapp.theme.color8
import com.example.budgetapp.ui.model.toUi
import kotlin.collections.get

@Composable
fun ExpensesRoute(
    vModel: BudgetViewModel,
    modifier: Modifier = Modifier
) {
    val budgetCents by vModel.budgetCents.collectAsState()
    val expenses by vModel.expenses.collectAsState()
    val categories by vModel.categories.collectAsState()

    ExpensesContent(
        expenses = expenses,
        categories = categories,
        onDeleteExpense = vModel::deleteExpense,
        modifier = modifier
    )
}

@Composable
fun ExpensesContent(
    expenses: List<Expense>,
    categories: List<Category>,
    onDeleteExpense: (Expense) -> Unit,
    modifier: Modifier = Modifier
) {
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedTime by remember { mutableStateOf(false) }

    var selectedCategory by remember { mutableStateOf("All") }
    var selectedTime by remember { mutableStateOf("This Month") }

    val categoryById = remember(categories) {
        categories.associateBy { it.id }
    }

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
                                    text = category.name,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    fontSize = mediumFontSize,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            onClick = {
                                selectedCategory = category.name
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


        // Expenses List
        LazyColumn(
            modifier = Modifier
                .fillMaxWidth(),
            //.weight(1f),
            verticalArrangement = Arrangement.spacedBy(0.dp)
        ) {
            items(
                expenses,
                key = { it.id }
            ) { expense ->
                val dismissState = rememberSwipeToDismissBoxState(
                    confirmValueChange = { value ->
                        if (value != SwipeToDismissBoxValue.Settled) {
                            onDeleteExpense(expense)
                            true
                        } else false
                    }
                )

                val categoryUi =
                    categoryById[expense.categoryId]?.toUi()
                        ?: CategoryUi("Uncategorized", MaterialTheme.colorScheme.tertiary)

                SwipeToDismissBox(
                    state = dismissState,
                    enableDismissFromStartToEnd = false,
                    backgroundContent = {
                        Row(
                            modifier = Modifier
                                .fillMaxSize()
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(end = 24.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            IconButton(onClick = { onDeleteExpense(expense) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            IconButton(onClick = { /* TODO edit */ }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Edit",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    content = {
                        ExpenseCard(
                            expense = expense,
                            category = categoryUi
                        )
                    }
                )
            }
        }
    }
}

@Preview
@Composable
fun ExpensesContentPreview() {
    BudgetAppTheme(false) {
        val previewCategories = listOf(
            Category(id = 1, name = "Groceries", color = color4.toArgb()),
            Category(id = 2, name = "Gas", color = color8.toArgb())
        )

        val previewExpenses = listOf(
            Expense(
                id = 1,
                categoryId = 1,
                description = "Trader Joe's",
                amountCents = 1539,
                dateEpochMillis = System.currentTimeMillis(),
                isRecurring = false
            ),
            Expense(
                id = 2,
                categoryId = 2,
                description = "Shell",
                amountCents = 4200,
                dateEpochMillis = System.currentTimeMillis(),
                isRecurring = false
            )
        )
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
            ExpensesContent(
                expenses = previewExpenses,
                categories = previewCategories,
                onDeleteExpense = {},
                modifier = Modifier.padding()
            )
        }
    }
}