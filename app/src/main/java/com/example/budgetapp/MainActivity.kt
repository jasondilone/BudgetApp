package com.example.budgetapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.BarChart
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.internal.isLiveLiteralsEnabled
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.budgetapp.ui.theme.BudgetAppTheme
import java.text.DecimalFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            Home()
        }
    }
}
data class Expense(
    val category: String,
    val amount: Double,
    val date: LocalDate
)

// sample expense/s
val expense = Expense("Shopping 🛍", 1888.80, LocalDate.now())
val expenses = mutableListOf<Expense>(
    Expense("Food 🍔", 23.15, LocalDate.now()),
    Expense("Bills 🧾", 233.98, LocalDate.now()),
    Expense("Food 🍔", 13.29, LocalDate.now()),
    Expense("Shopping 🛍", 48.03, LocalDate.now()),
    Expense("Subscriptions 🔁", 14.99, LocalDate.now()),
    Expense("Bills 🧾", 69.95, LocalDate.now())
)

// Global Variables
val spent: Double = 374.70
val budget: Double = 1000.00
val lightPurpleColor = Color(97, 111, 219)
val lightTealColor = Color(207, 220, 225)
val TealColor = Color(115, 170, 197)

// change fontSize depending on number of characters
// for spent $ and budget $
// so it fits in a Surface()
fun autoFontSize(text: String): TextUnit {
    val fontSize = when {
        text.length <= 8 -> 30.sp
        text.length <= 10 -> 25.sp
        text.length <= 12 -> 20.sp
        else -> 15.sp
    }
    return fontSize
}
@Composable
fun HomeSpentAndBudget(spent: Double, budget: Double) {
    val formatter = DecimalFormat("#,###.00")
    val spentText = formatter.format(spent)
    val budgetText = formatter.format(budget)
    Row(
        modifier = Modifier.fillMaxWidth().padding(top = 50.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp)
    ) {

        // "Spent" box
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = lightPurpleColor,
            modifier = Modifier
                .width(180.dp)
                .height(90.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                "Spent",
                modifier = Modifier.padding(all = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White
            )
            Text(
                text = "$$spentText",
                modifier = Modifier.padding(all = 30.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = autoFontSize(spentText),
                maxLines = 1
            )
        }

        // "Budget box
        Surface(
            shape = RoundedCornerShape(16.dp),
            color = lightPurpleColor,
            modifier = Modifier
                .fillMaxWidth()
                .height(90.dp),
            border = BorderStroke(2.dp, Color.Black)
        ) {
            Text(
                "Budget",
                modifier = Modifier.padding(all = 10.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
            )
            Text(
                text = "$$budgetText",
                modifier = Modifier.padding(all = 30.dp)
                    .wrapContentWidth(Alignment.CenterHorizontally),
                color = Color.White,
                fontSize = autoFontSize(budgetText),
                maxLines = 1
            )
        }
    }
}
@Composable
fun HomeBudgetRing() {
    val spentPercentage: Float = 100 * (spent/budget).toFloat()
    val ringAngle: Float = 360 * (spent/budget).toFloat()
    val percentageNoDecimals = "%.0f".format(spentPercentage)
    Column(
        modifier = Modifier.fillMaxWidth()
            .height(150.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            contentAlignment = Alignment.Center,
            modifier = Modifier.size(200.dp)
        ) {
            Text(
                text = "$percentageNoDecimals%",
                color = Color.Black,
                fontSize = 40.sp
            )
            Canvas(modifier = Modifier.size(150.dp)) {

                // Outline of ring
                drawArc(
                    color = Color.Black,
                    startAngle = -270f,
                    sweepAngle = ringAngle,
                    useCenter = false,
                    style = Stroke(
                        width = 111.0f,
                        cap = StrokeCap.Round)
                )

                // ring
                drawArc(
                    color = TealColor,
                    startAngle = -270f,
                    sweepAngle = ringAngle,
                    useCenter = false,
                    style = Stroke(
                        width = 100.0f,
                        cap = StrokeCap.Round)
                )
            }
        }
    }
}
@Composable
fun ExpenseCard(expense: Expense) {
    val dateFormatter = DateTimeFormatter.ofPattern("MMM dd, yyyy")
    val formatter = DecimalFormat("#,###.00")
    val expenseAmountText = formatter.format(expense.amount)

    Surface(
        shape = RoundedCornerShape(16.dp),
        modifier = Modifier.width(330.dp)
            .height(100.dp),
        color = lightPurpleColor,
        border = BorderStroke(2.dp, Color.Black)
    ) {
        Row {
            Column {
                Text(
                    text = expense.category,
                    fontSize = 18.sp,
                    modifier = Modifier.padding(10.dp)
                        .padding(horizontal = 20.dp),
                    color = Color.White
                )
                Text(
                    text = "$$expenseAmountText",
                    fontSize = 35.sp,
                    modifier = Modifier.padding(horizontal = 40.dp)
                        .padding(bottom = 15.dp),
                    color = Color.White
                )
            }
            Column(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = expense.date.format(dateFormatter),
                    modifier = Modifier.fillMaxWidth()
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
fun HomeExpenses(expenses: List<Expense>) {
    LazyColumn(
        modifier = Modifier.fillMaxWidth(),
        verticalArrangement = Arrangement.spacedBy(10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(expenses) { expense ->
            ExpenseCard(expense)
        }
    }
}
@Composable
fun BottomNavigationBar() {
    var selectedButton = remember { mutableStateOf(0) }
    Surface(
        modifier = Modifier.fillMaxWidth()
            .height(100.dp),
        color = lightTealColor
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {
            // -------Home Icon-------
            IconButton(
                onClick = {
                    selectedButton.value = 0
                },
                modifier = Modifier.size(60.dp)
                    .background(
                        if(selectedButton.value == 0)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(2.dp,
                            if(selectedButton.value == 0)
                                Color.Black else Color.Transparent),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Home,
                    contentDescription = "Home Icon",
                    modifier = Modifier.size(35.dp),
                )
            }

            // -------Expenses list Icon-------
            IconButton(
                onClick = {
                    selectedButton.value = 1
                },
                modifier = Modifier.size(60.dp)
                    .background(
                        if(selectedButton.value == 1)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(2.dp,
                            if(selectedButton.value == 1)
                                Color.Black else Color.Transparent),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.List,
                    contentDescription = "Expenses Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            // -------Add Expense Icon-------
            IconButton(
                onClick = {
                    selectedButton.value = 2
                },
                modifier = Modifier.size(60.dp)
                    .background(
                        if(selectedButton.value == 2)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(2.dp,
                            if(selectedButton.value == 2)
                                Color.Black else Color.Transparent),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.Add,
                    contentDescription = "Add expenses Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            // -------Charts Icon-------
            IconButton(
                onClick = {
                    selectedButton.value = 3
                },
                modifier = Modifier.size(60.dp)
                    .background(
                        if(selectedButton.value == 3)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(2.dp,
                            if(selectedButton.value == 3)
                                Color.Black else Color.Transparent),
                        CircleShape
                    )
            ) {
                Icon(
                    imageVector = Icons.Filled.BarChart,
                    contentDescription = "Charts Icon",
                    modifier = Modifier.size(35.dp)
                )
            }

            // -------Settings Icon-------
            IconButton(
                onClick = {
                    selectedButton.value = 4
                },
                modifier = Modifier.size(60.dp)
                    .background(
                        if(selectedButton.value == 4)
                            TealColor else Color.Transparent,
                        shape = CircleShape
                    )
                    .border(
                        BorderStroke(2.dp,
                            if(selectedButton.value == 4)
                                Color.Black else Color.Transparent),
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
    Scaffold(
        bottomBar = {BottomNavigationBar()}
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(all = 8.dp)
                .padding(innerPadding)
        ) {
            HomeSpentAndBudget(spent, budget)
            Spacer(Modifier.height(70.dp))
            HomeBudgetRing()
            Spacer(Modifier.height(70.dp))
            HomeExpenses(expenses)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun HomePreview() {
    BudgetAppTheme {
        Home()
    }
}

/*
@Preview(showBackground = true)
@Composable
fun ExpenseCardPreview() {
    ExpenseCard(expense)
}
 */