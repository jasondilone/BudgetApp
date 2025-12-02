package com.example.budgetapp

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
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
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
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
import androidx.navigation.compose.rememberNavController
import com.example.budgetapp.theme.BudgetAppTheme

@Composable
fun Add(modifier: Modifier = Modifier) {
    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedCategorySelection by remember { mutableStateOf(false) }
    var typeSelection by remember { mutableStateOf(R.string.expense) }
    var amountInput by remember { mutableStateOf("") }
    var isRecurring by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {

        // Add Expense/Category
        Text(
            text = stringResource(R.string.add_expense_and_category),
            fontSize = largeFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Type Selection
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
                            text = stringResource(R.string.expense),
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
        Spacer(modifier = Modifier.height(80.dp))

        // Amount
        Text(
            text = stringResource(R.string.amount),
            fontSize = mediumFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
                .padding(bottom = 12.dp)
        )

        Surface(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .height(50.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.primary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier.padding(0.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = stringResource(R.string.dollar_sign),
                    fontSize = mediumFontSize,
                    color = MaterialTheme.colorScheme.tertiary,
                    maxLines = 1,
                    modifier = Modifier.padding(horizontal = 20.dp)
                )
            }
        }

        // Description
        Text(
            text = stringResource(R.string.description),
            fontSize = mediumFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
                .padding(bottom = 12.dp)
        )

        Surface(
            modifier = Modifier.fillMaxWidth()
                .padding(horizontal = 10.dp)
                .padding(bottom = 10.dp)
                .height(50.dp),
            shape = RoundedCornerShape(roundDp),
            color = MaterialTheme.colorScheme.primary,
            border = BorderStroke(0.dp, MaterialTheme.colorScheme.secondary)
        ) {
            Row(
                modifier = Modifier.padding(0.dp)
                    .fillMaxWidth()
                    .height(IntrinsicSize.Min)
                    .align(Alignment.Start),
                verticalAlignment = Alignment.CenterVertically
            ) {

            }
        }

        // Category
        Text(
            text = stringResource(R.string.category),
            fontSize = mediumFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        // Category Selection
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
                            text = stringResource(R.string.other),
                            fontSize = mediumFontSize,
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                // Dropdown
                IconButton(
                    onClick = { expandedCategorySelection = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expandedCategorySelection,
                    onDismissRequest = { expandedCategorySelection = false },
                    modifier = Modifier
                        .width(330.dp)
                        .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(roundDp))
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp))
                ) {

                }
            }
        }

        Row(
            modifier = Modifier.fillMaxWidth()
                .height(50.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = stringResource(R.string.recurring),
                fontSize = mediumFontSize,
                modifier = Modifier.padding(horizontal = 10.dp)
            )
            Spacer(modifier = Modifier.weight(1f))
            Switch(
                checked = isRecurring,
                onCheckedChange = { isRecurring = it },
                modifier = Modifier
                    .fillMaxHeight()
                    .padding(horizontal = 20.dp),
                colors = SwitchDefaults.colors(
                    checkedThumbColor = MaterialTheme.colorScheme.primary,
                    checkedTrackColor = MaterialTheme.colorScheme.secondary,
                    uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                    uncheckedTrackColor = MaterialTheme.colorScheme.primary,
                    uncheckedBorderColor = MaterialTheme.colorScheme.secondary
                )
            )
        }


        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(20.dp),
            horizontalArrangement = Arrangement.Center
        ) {
            Button(
                onClick = {  },
                shape = RoundedCornerShape(64.dp),
                modifier = Modifier.size(180.dp, 50.dp),
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
            ) {
                Text(
                    text = stringResource(R.string.add),
                    fontSize = mediumFontSize,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun AddPreview() {

    BudgetAppTheme(false) {
        Scaffold(
            bottomBar = {
                Box (
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = 8.dp, bottom = 32.dp)
                ) {
                    NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Add(modifier = Modifier.padding(innerPadding))
        }
    }
}