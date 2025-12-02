package com.example.budgetapp

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
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.theme.orangeColor
import java.time.LocalDate

@Composable
fun Home(modifier: Modifier = Modifier, spent: Double, budget: Int) {
    // budget button
    var budget by remember { mutableStateOf(500.0) } //<--- deefault budg val
    var showBudgetDialog by remember { mutableStateOf(false) }
    var newBudgetText by remember { mutableStateOf("") }
    // for ring's dark mode colors

    val materialPrimary = MaterialTheme.colorScheme.primary
    val materialBackground = MaterialTheme.colorScheme.background
    val materialSecondary = MaterialTheme.colorScheme.secondary

    // Sample Data
    val expenses = remember {
        mutableListOf<Expense>(
            Expense("Food",
                "deli",
                26.02,
                LocalDate.now(),
                false
            ),
            Expense("Subscription",
                "Prime",
                11.10,
                LocalDate.now(),
                true
            ),
            Expense("Shopping",
                "Amazon",
                39.99,
                LocalDate.now(),
                false
            ),
            Expense("Shopping",
                "Best Buy",
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
            )
        ) }

    var spentPercentage = ((spent /budget) * 100).toInt()
    var ringPercent: Float = ((spent /budget) * 360).toFloat()
    var percentColor = when (spentPercentage) {
        in 0..79 -> MaterialTheme.colorScheme.onBackground
        in 80..99 -> orangeColor
        else -> MaterialTheme.colorScheme.onSurfaceVariant
    }

    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {

        // Home
        Text(
            text = stringResource(R.string.home),
            fontSize = largeFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Spent | Budget
        Surface(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.secondary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.tertiary)
        ) {
            Row(
                modifier = Modifier.padding(10.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {

                // Spent Column
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
                        text = "$" + centsNumberFormatter.format(spent),
                        fontSize = mediumFontSize,
                        maxLines = 1,
                        color = MaterialTheme.colorScheme.onSecondary
                    )
                }

                // Line in between Spent and Budget
                Canvas(
                    modifier = Modifier.padding(0.dp)
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

                // Budget Column
                Column(
                    modifier = Modifier.weight(1f),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(
                        text = "" + stringResource(R.string.budget),
                        fontSize = mediumFontSize,
                        color = MaterialTheme.colorScheme.primary
                    )
                    Text(
                        text = "$" + noCentsNumberFormatter.format(budget),
                        fontSize = mediumFontSize,
                        maxLines = 1,
                        //added budget clcikable
                        modifier = Modifier.clickable { showBudgetDialog = true }
                    )
                }
            }
        }

        // Ring
        Box(
            modifier = Modifier.fillMaxWidth().padding(10.dp),
            contentAlignment = Alignment.Center
        ) {
            Canvas(
                modifier = Modifier.size(200.dp)
            ) {
                val strokeWidth = 65f
                val diameter = size.minDimension
                val arcSize = Size(
                    width = diameter - strokeWidth,
                    height = diameter - strokeWidth
                )
                val topLeft = Offset(
                    (size.width - arcSize.width)/2f,
                    (size.height - arcSize.height)/2f
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
                text = noCentsNumberFormatter.format(spentPercentage) + "%",
                fontSize = largeFontSize,
                color = percentColor
            )
        }
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = stringResource(R.string.recent_expenses),
                fontSize = mediumFontSize,
                modifier = Modifier.padding(horizontal = 10.dp)
                    .padding(bottom = 12.dp)
            )
        }

        //added swipe to delete to new ui
        LazyColumn(
            modifier = Modifier.padding(0.dp),
            verticalArrangement = Arrangement.spacedBy(0.dp)
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
                                .background(MaterialTheme.colorScheme.surfaceVariant)
                                .padding(end = 24.dp),
                            horizontalArrangement = Arrangement.End,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            // DELETE
                            IconButton(onClick = { expenses.remove(expense) }) {
                                Icon(
                                    imageVector = Icons.Default.Close,
                                    contentDescription = "Delete",
                                    tint = MaterialTheme.colorScheme.error
                                )
                            }
                            Spacer(modifier = Modifier.width(16.dp))
                            // EDIT
                            IconButton(onClick = {
                                // adding edit (maybe)
                            }) {
                                Icon(
                                    imageVector = Icons.Default.Settings,
                                    contentDescription = "Edit",
                                    tint = MaterialTheme.colorScheme.primary
                                )
                            }
                        }
                    },
                    content = {
                        ExpenseCard(expense = expense)
                    }
                )
            }
        }
    }
    //EDITING BUDGET
    //need to change colors for the alertbox and ok/canvel
    if (showBudgetDialog) {
        AlertDialog(
            onDismissRequest = { showBudgetDialog = false },
            title = { Text("Set Budget") },
            text = {
                OutlinedTextField(
                    value = newBudgetText,
                    onValueChange = { newBudgetText = it },
                    label = { Text("Enter new budget") }
                )
            },
            confirmButton = {
                TextButton(onClick = {
                    val parsed = newBudgetText.toDoubleOrNull()
                    if (parsed != null) {
                        budget = parsed
                    }
                    showBudgetDialog = false
                    newBudgetText = ""
                }) {
                    Text("OK")
                }
            },
            dismissButton = {
                TextButton(onClick = {
                    showBudgetDialog = false
                    newBudgetText = ""
                }) {
                    Text("Cancel")
                }
            }
        )
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BudgetAppTheme(false) {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier.background(MaterialTheme.colorScheme.background)
                        .padding(horizontal = 20.dp)
                        .padding(top = 8.dp, bottom = 32.dp)
                ) {
                    NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Home(
                modifier = Modifier.padding(innerPadding),
                budget = budget,
                spent = spent
            )
        }
    }
}