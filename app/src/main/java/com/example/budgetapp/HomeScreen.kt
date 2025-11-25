package com.example.budgetapp.ui

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.*
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.*

import com.example.budgetapp.ui.theme.BudgetAppTheme
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

import androidx.compose.foundation.background

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier


//imports for data in mainact and colors.kt, also moved hgomespenandbudged and homebudgetring
// to HomeUiData.kt becuase of conflicting//overloading compiling issues
import com.example.budgetapp.ui.theme.lightTealColor
import com.example.budgetapp.ui.theme.lightPurpleColor
import com.example.budgetapp.ui.theme.TealColor

import com.example.budgetapp.Expense
import com.example.budgetapp.expenses
import com.example.budgetapp.sampleExpense
import com.example.budgetapp.spent
import com.example.budgetapp.budget
import com.example.budgetapp.autoFontSize

import com.example.budgetapp.HomeSpentAndBudget
import com.example.budgetapp.HomeBudgetRing

@Composable
fun ExpenseCard(expense: Expense) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    val formatter = DecimalFormat("#,###.00")
    val expenseAmountText = formatter.format(expense.amount)

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier
            .width(330.dp)
            .height(100.dp),
        color = lightPurpleColor,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row {
            Column {
                Text(
                    text = expense.category,
                    fontSize = 18.sp,
                    modifier = Modifier
                        .padding(10.dp)
                        .padding(horizontal = 20.dp),
                    color = Color.White
                )
                Text(
                    text = "$$expenseAmountText",
                    fontSize = 35.sp,
                    modifier = Modifier
                        .padding(horizontal = 40.dp)
                        .padding(bottom = 15.dp),
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = expense.date.format(dateFormatter),
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(15.dp)
                        .padding(horizontal = 25.dp),
                    fontSize = 14.sp,
                    color = Color.White
                )
            }
        }
    }
}

@Composable
fun HomeExpenses(expenses: List<Expense>, onDelete: (Expense) -> Unit, onEdit: (Expense) -> Unit) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(expenses, key = { it.hashCode() }) { expense ->
            val dismissState = rememberSwipeToDismissBoxState(
                confirmValueChange = { value ->
                    value != SwipeToDismissBoxValue.Settled
                }
            )

            SwipeToDismissBox(
                state = dismissState,
                enableDismissFromStartToEnd = false,
                backgroundContent = {
                    Row(
                        modifier = Modifier
                            .fillMaxSize()
                            .background(Color.LightGray)
                            .padding(end = 24.dp),
                        horizontalArrangement = Arrangement.End,
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        //DELETE
                        IconButton(onClick = { onDelete(expense) }) {
                            Icon(
                                imageVector = Icons.Default.Close,
                                contentDescription = "Delete",
                                tint = Color.Red
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        //EDIT
                        IconButton(onClick = { onEdit(expense) }) {
                            Icon(
                                imageVector = Icons.Default.Settings,
                                contentDescription = "Edit",
                                tint = Color.Blue
                            )
                        }
                    }
                },
                content = {
                    ExpenseCard(expense)
                }
            )
        }
    }
}


@Composable
fun BottomNavigationBar(selectedIndex: Int = 0) {

    var selectedButton = remember { mutableStateOf(selectedIndex) }
    Surface(
        modifier = Modifier
            .fillMaxWidth()
            .height(100.dp),
        color = lightTealColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            //HOME ICON
            IconButton(
                onClick = {
                    selectedButton.value = 0
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (selectedButton.value == 0)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            if (selectedButton.value == 0)
                                Color.Black else Color.Transparent
                        ),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(35.dp),
                )
            }

            // EXPENSE ITEM LIST
            IconButton(
                onClick = {
                    selectedButton.value = 1
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (selectedButton.value == 1)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            if (selectedButton.value == 1)
                                Color.Black else Color.Transparent
                        ),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "Expenses Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            // ADD EXPENSE ICON
            IconButton(
                onClick = {
                    selectedButton.value = 2
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (selectedButton.value == 2)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            if (selectedButton.value == 2)
                                Color.Black else Color.Transparent
                        ),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add expenses Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            //CHARTS ICON
            IconButton(
                onClick = {
                    selectedButton.value = 3
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (selectedButton.value == 3)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            if (selectedButton.value == 3)
                                Color.Black else Color.Transparent
                        ),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.BarChart,
                    contentDescription = "Charts Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            //SETTINGS ICON
            IconButton(
                onClick = {
                    selectedButton.value = 4
                },
                modifier = Modifier
                    .size(60.dp)
                    .background(
                        if (selectedButton.value == 4)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(
                            2.dp,
                            if (selectedButton.value == 4)
                                Color.Black else Color.Transparent
                        ),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Settings,
                    contentDescription = "Settings Icon",
                    modifier = Modifier.size(35.dp)
                )
            }
        }
    }
}

@Composable
fun Home() {
    val expensesState = remember { mutableStateListOf<Expense>().apply { addAll(expenses) } }

    Scaffold(bottomBar = { BottomNavigationBar() }) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(innerPadding)
        ) {
            HomeSpentAndBudget(spent, budget)
            Spacer(Modifier.height(70.dp))
            HomeBudgetRing()
            Spacer(Modifier.height(70.dp))
            HomeExpenses(
                expenses = expensesState,
                onDelete = { expenseToDelete -> expensesState.remove(expenseToDelete) },
                onEdit = { expenseToEdit ->
                    // gear option on left swipe, editing option (maybe)
                }
            )
        }
    }
}


/*
@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BudgetAppTheme {
        Home()
    }
}
 */

