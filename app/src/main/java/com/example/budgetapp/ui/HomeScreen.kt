package com.example.budgetapp.ui

import androidx.appcompat.app.AlertDialog
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.SwipeToDismissBox
import androidx.compose.material3.SwipeToDismissBoxValue
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.rememberSwipeToDismissBoxState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.roundDp
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.theme.orangeColor
import com.example.budgetapp.utility.formatCents
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.theme.color4
import com.example.budgetapp.theme.color8
import com.example.budgetapp.ui.model.toUi


@Composable
fun HomeRoute(
    vModel: BudgetViewModel,
    modifier: Modifier = Modifier
) {
    val budgetCents by vModel.budgetCents.collectAsState()
    val expenses by vModel.expenses.collectAsState()
    val categories by vModel.categories.collectAsState()

    HomeContent(
        budgetCents = budgetCents,
        expenses = expenses,
        categories = categories,
        onDeleteExpense = vModel::deleteExpense,
        onSetBudget = vModel::updateBudgetCents,
        modifier = modifier
    )
}

@Composable
fun HomeContent(
    budgetCents: Long,
    expenses: List<Expense>,
    categories: List<Category>,
    onDeleteExpense: (Expense) -> Unit,
    onSetBudget: (Long) -> Unit,
    modifier: Modifier = Modifier
) {
    var showBudgetDialog by remember { mutableStateOf(false) }
    var newBudgetText by remember { mutableStateOf("") }

    val materialPrimary = MaterialTheme.colorScheme.primary
    val materialBackground = MaterialTheme.colorScheme.background
    val materialSecondary = MaterialTheme.colorScheme.secondary

    val spentCents = expenses.sumOf { it.amountCents }
    val progress =
        if (budgetCents == 0L) 0f
        else (spentCents.toFloat() / budgetCents.toFloat())//.coerceIn(0f, 1f)

    val categoryById = remember(categories) {
        categories.associateBy { it.id }
    }

    val spentPercentage = (progress * 100).toInt()
    val ringPercent = progress * 360f

    val percentColor = when (spentPercentage) {
        in 0..79 -> MaterialTheme.colorScheme.onBackground
        in 80..99 -> orangeColor
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = modifier
            .fillMaxSize()
            .padding(top = 25.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.home),
            fontSize = largeFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Spent | Budget
        Surface(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.secondary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            Row(
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.spent),
                        fontSize = mediumFontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = formatCents(spentCents),
                        fontSize = mediumFontSize,
                        maxLines = 1,
                        color = if(percentColor == MaterialTheme.colorScheme.onBackground) {
                            Color.White
                        } else {
                            percentColor
                        }
                    )
                }

                Canvas(
                    modifier = Modifier
                        .fillMaxHeight()
                        .width(1.dp)
                ) {
                    drawLine(
                        color = materialPrimary,
                        strokeWidth = 8f,
                        start = Offset(size.width / 2f, 0f),
                        end = Offset(size.width / 2f, size.height)
                    )
                }

                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = stringResource(R.string.budget),
                        fontSize = mediumFontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = formatCents(budgetCents),
                        fontSize = mediumFontSize,
                        maxLines = 1,
                        modifier = Modifier
                            .clickable { showBudgetDialog = true }
                            .padding(8.dp)
                    )
                }
            }
        }
        //setting budget
        if (showBudgetDialog) {
            AlertDialog(
                onDismissRequest = { showBudgetDialog = false },
                title = {
                    Text(
                        "Set Budget",
                        color = MaterialTheme.colorScheme.secondary
                    )
                },
                text = {
                    OutlinedTextField(
                        value = newBudgetText,
                        onValueChange = { newBudgetText = it },
                        label = {
                            Text(
                                "Enter budget in dollars",
                                color = MaterialTheme.colorScheme.secondary
                            )
                        },
                        textStyle = LocalTextStyle.current.copy(
                            color = MaterialTheme.colorScheme.secondary
                        )
                    )
                },
                confirmButton = {
                    TextButton(
                        onClick = {
                            val cents = newBudgetText.toLongOrNull()?.times(100) ?: 0L
                            onSetBudget(cents)
                            showBudgetDialog = false
                        },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Confirm")
                    }
                },
                dismissButton = {
                    TextButton(
                        onClick = { showBudgetDialog = false },
                        colors = ButtonDefaults.textButtonColors(
                            contentColor = MaterialTheme.colorScheme.secondary
                        )
                    ) {
                        Text("Cancel")
                    }
                },
                containerColor = MaterialTheme.colorScheme.surface
            )
        }


        // Ring
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(modifier = Modifier.size(200.dp)) {
                val strokeWidth = 65f
                val diameter = size.minDimension
                val arcSize = Size(
                    width = diameter - strokeWidth,
                    height = diameter - strokeWidth
                )
                val topLeft = Offset(
                    (size.width - arcSize.width) / 2f,
                    (size.height - arcSize.height) / 2f
                )
                drawCircle(color = materialPrimary)
                drawCircle(color = materialBackground, radius = 200f)
                drawArc(
                    color = materialSecondary,
                    startAngle = 270f,
                    sweepAngle = ringPercent,
                    useCenter = false,
                    topLeft = topLeft,
                    size = arcSize,
                    style = Stroke(strokeWidth)
                )
            }
            Text(
                text = "$spentPercentage%",
                fontSize = largeFontSize,
                color = percentColor
            )
        }

        Row(modifier = Modifier.fillMaxWidth()) {
            Text(
                text = stringResource(R.string.recent_expenses),
                fontSize = mediumFontSize,
                modifier = Modifier
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 12.dp)
            )
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

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BudgetAppTheme(darkTheme = false) {
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

        Scaffold { innerPadding ->
            HomeContent(
                budgetCents = 100_000, // $1000.00
                expenses = previewExpenses,
                categories = previewCategories,
                onDeleteExpense = {},
                onSetBudget = {},
                modifier = Modifier.padding()
            )
        }
    }
}