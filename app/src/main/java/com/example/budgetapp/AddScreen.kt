package com.example.budgetapp

import android.graphics.drawable.shapes.OvalShape
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.absoluteOffset
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.text.DecimalFormat

import com.example.budgetapp.ui.theme.lightTealColor
import com.example.budgetapp.ui.theme.TealColor
import com.example.budgetapp.ui.BottomNavigationBar



@Composable
fun AddScreen() {

    // selection will show "Expense" by default but can change to "Category"
    var selection by remember {mutableStateOf("Expense")}
    var expanded by remember {mutableStateOf(false)}
    var amountInput by remember {mutableStateOf("")}
    val currencyFormatter = remember { DecimalFormat("#,##0.00") }
    Scaffold(
        bottomBar = {BottomNavigationBar(2)}
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .padding(all = 8.dp)
                .padding(innerPadding)
                .fillMaxWidth()
        ) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                modifier = Modifier
                    .width(430.dp)
                    .height(100.dp)
                    .absoluteOffset(0.dp, 50.dp),
                color = lightTealColor,
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = selection,
                    fontSize = 40.sp,
                    modifier = Modifier.padding(25.dp),
                    textAlign = TextAlign.Center
                )
                IconButton(onClick = { expanded = true },
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = "Dropdown arrow",
                        modifier = Modifier
                            .size(40.dp)
                            .absoluteOffset(120.dp)
                    )
                }
                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false},
                    modifier = Modifier
                        .width(330.dp)
                        .border(2.dp, Color.Black, RoundedCornerShape(16.dp))
                        .background(lightTealColor, RoundedCornerShape(16.dp))
                        .absoluteOffset(90.dp),
                    offset = DpOffset(20.dp, 5.dp)

                ) {
                    DropdownMenuItem(
                        onClick = {
                            expanded = false;
                            selection = "Expense"
                        },
                        text = {Text(stringResource(R.string.expense),
                            fontSize = 30.sp
                            )}
                    )
                    DropdownMenuItem(
                        onClick = {
                            expanded = false;
                            selection = "Category"
                                  },
                        text = {Text(stringResource(R.string.category),
                            fontSize = 30.sp
                            )}
                    )
                }
            }

            // Text for "Expense"
            Text(
                text = if(selection == "Expense") {"Amount"}
                       else {"Name"},
                fontSize = 20.sp,
                modifier = Modifier.absoluteOffset(50.dp, 190.dp)
            )
            TextField(
                textStyle = TextStyle(
                    fontSize = 40.sp
                ),
                leadingIcon = { Text(
                    if(selection == "Expense") "$" else "", fontSize = 40.sp
                ) },
                //placeholder = { Text(stringResource(R.string.amount),
                //    fontSize = 40.sp) },
                value = amountInput,
                onValueChange = { newValue ->
                    if (selection == "Expense") {
                        val digits = newValue.filter { it.isDigit() }
                        if (digits.isEmpty()) {
                            amountInput = ""
                        } else {
                            val cents = digits.toLong()
                            val value = cents / 100.0
                            amountInput = currencyFormatter.format(value)
                        }
                    } else {
                        amountInput = newValue
                    }
                },
                modifier = Modifier
                    .width(300.dp)
                    .height(100.dp)
                    .absoluteOffset(35.dp, 200.dp)
                    .border(2.dp, Color.Black, RoundedCornerShape(16.dp)),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = if (selection == "Expense") KeyboardType.Number
                                   else KeyboardType.Text,
                    imeAction = ImeAction.Next
                ),
                singleLine = true,
                shape = RoundedCornerShape(16.dp)
            )
            Button(
                onClick = {  },
                modifier = Modifier
                    .width(200.dp)
                    .height(90.dp)
                    .align(Alignment.CenterHorizontally)
                    .absoluteOffset(0.dp, 370.dp),
                colors = ButtonDefaults.buttonColors(TealColor, Color.White),
                border = BorderStroke(2.dp, Color.Black)
            ) {
                Text(
                    text = "Add",
                    fontSize = 40.sp
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddPreview() {
    AddScreen()
}