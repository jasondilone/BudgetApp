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
import androidx.compose.foundation.layout.fillMaxHeight
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
import androidx.compose.material.icons.outlined.ColorLens
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.roundDp
import com.example.budgetapp.theme.BudgetAppTheme
import com.example.budgetapp.theme.color1
import com.example.budgetapp.theme.color10
import com.example.budgetapp.theme.color11
import com.example.budgetapp.theme.color12
import com.example.budgetapp.theme.color2
import com.example.budgetapp.theme.color3
import com.example.budgetapp.theme.color4
import com.example.budgetapp.theme.color5
import com.example.budgetapp.theme.color6
import com.example.budgetapp.theme.color7
import com.example.budgetapp.theme.color8
import com.example.budgetapp.theme.color9

@Composable
fun Add(
    modifier: Modifier = Modifier
) {
    var expandedType by remember { mutableStateOf(false) }
    var expandedCategory by remember { mutableStateOf(false) }
    var expandedCategorySelection by remember { mutableStateOf(false) }
    var selectedType by remember { mutableStateOf("Expense") }
    var amountInput by remember { mutableStateOf("") }
    var isRecurring by remember { mutableStateOf(false) }
    val types = listOf("Expense", "Category")

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
                    //.fillMaxWidth()
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
                            text = selectedType,
                            fontSize = mediumFontSize,
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                // Dropdown
                IconButton(
                    onClick = { expandedType = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expandedType,
                    onDismissRequest = { expandedType = false },
                    modifier = Modifier
                        .padding(horizontal = 10.dp)
                        .border(
                            0.dp,
                            MaterialTheme.colorScheme.onPrimary,
                            RoundedCornerShape(roundDp)
                        )
                        .width(370.dp),
                    containerColor = MaterialTheme.colorScheme.secondary,
                    shape = RoundedCornerShape(roundDp)
                ) {
                    types.forEach { type ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = type,
                                    color = MaterialTheme.colorScheme.onTertiary,
                                    fontSize = mediumFontSize,
                                    modifier = Modifier.fillMaxWidth(),
                                    textAlign = TextAlign.Center
                                )
                            },
                            onClick = {
                                selectedType = type
                                expandedType = false
                            }
                        )
                    }
                }
            }
        }
        Spacer(modifier = Modifier.height(80.dp))

        /*
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
*/
        // ------Amount Input--------
        if (selectedType == "Expense") {
            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Icon(
                    painter = painterResource(R.drawable.ic_dollar_sign),
                    contentDescription = null,
                    modifier = Modifier.padding(top = 5.dp)
                        .size(27.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )

                // ---- Amount Input --------
                var amountInput by remember { mutableStateOf("") }

                OutlinedTextField(
                    label = { Text(stringResource(R.string.amount)) },
                    value = amountInput,
                    onValueChange = {
                        amountInput = it
                    },
                    textStyle = LocalTextStyle.current.copy(fontSize = mediumFontSize),
                    modifier = Modifier.fillMaxWidth()
                        .height(75.dp)
                        .padding(horizontal = 10.dp)
                        .padding(bottom = 10.dp),
                    shape = RoundedCornerShape(roundDp),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedContainerColor = MaterialTheme.colorScheme.primary,
                        unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                        focusedBorderColor = MaterialTheme.colorScheme.secondary,
                        unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                        focusedTextColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                        focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                        unfocusedLabelColor = MaterialTheme.colorScheme.tertiary
                    ),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Decimal),
                )
            }
        }

        // -------Description / Name input---------
        Row(
            modifier = Modifier.fillMaxWidth()
                .padding(start = 10.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Edit,
                contentDescription = null,
                modifier = Modifier.padding(top = 5.dp)
                    .size(27.dp),
                tint = MaterialTheme.colorScheme.tertiary
            )

            // ---- Description Input --------
            var description by remember { mutableStateOf("") }

            OutlinedTextField(
                label = { Text(stringResource(
                    if (selectedType == "Expense") {
                        R.string.description
                    } else {
                        R.string.name
                    }
                )) },
                value = description,
                onValueChange = {
                    description = it
                },
                textStyle = LocalTextStyle.current.copy(fontSize = mediumFontSize),
                modifier = Modifier.fillMaxWidth()
                    .height(75.dp)
                    .padding(horizontal = 10.dp)
                    .padding(bottom = 10.dp)
                ,
                shape = RoundedCornerShape(roundDp),
                colors = OutlinedTextFieldDefaults.colors(
                    focusedContainerColor = MaterialTheme.colorScheme.primary,
                    unfocusedContainerColor = MaterialTheme.colorScheme.primary,
                    focusedBorderColor = MaterialTheme.colorScheme.secondary,
                    unfocusedBorderColor = MaterialTheme.colorScheme.secondary,
                    focusedTextColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedTextColor = MaterialTheme.colorScheme.tertiary,
                    focusedLabelColor = MaterialTheme.colorScheme.tertiary,
                    unfocusedLabelColor = MaterialTheme.colorScheme.tertiary
                ),
                singleLine = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Text)
            )
        }

        // Category
        val categories = listOf("Food", "Transportation", "Entertainment", "Other")
        var selectedCategory by remember { mutableStateOf("Category") }
        var expandedCategorySelection by remember { mutableStateOf(false) }

        // Category Selection
        if (selectedType == "Expense") {
            Surface(
                modifier = Modifier.fillMaxWidth().padding(10.dp)
                    .padding(start = 37.dp),
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
                            .border(0.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(
                                roundDp
                            )),
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
                                    expandedCategorySelection = false
                                }
                            )
                        }
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
                    modifier = Modifier.padding(horizontal = 10.dp),
                    color = MaterialTheme.colorScheme.tertiary
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
        }

        // Be able to select Color for category
        if (selectedType == "Category") {
            val colorOptions = listOf(
                color1, color2, color3,
                color4, color5, color6,
                color7, color8, color9,
                color10, color11, color12,
                MaterialTheme.colorScheme.secondary
            )
            var selectedColor by remember { mutableStateOf(colorOptions[0]) }
            var expandedColor by remember { mutableStateOf(false) }

            Row(
                modifier = Modifier.fillMaxWidth()
                    .padding(start = 10.dp)
            ) {
                Icon(
                    imageVector = Icons.Outlined.ColorLens,
                    contentDescription = null,
                    modifier = Modifier.padding(top = 5.dp)
                        .size(27.dp),
                    tint = MaterialTheme.colorScheme.tertiary
                )

                // ---- Color selection --------

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
                            Box(
                                modifier = Modifier
                                    .padding(start = 10.dp)
                                    .size(34.dp)
                                    .background(
                                        selectedColor,
                                        shape = RoundedCornerShape(50)
                                    )
                                    .border(
                                        1.dp,
                                        MaterialTheme.colorScheme.secondary,
                                        RoundedCornerShape(50)
                                    )
                            )
                            Column(
                                modifier = Modifier.weight(1f),
                                horizontalAlignment = Alignment.CenterHorizontally
                            ) {
                                Text(
                                    text = "Color",
                                    fontSize = mediumFontSize,
                                    color = MaterialTheme.colorScheme.tertiary,
                                    maxLines = 1,
                                    modifier = Modifier.padding(start = 8.dp)
                                )
                            }
                        }

                        // Dropdown
                        IconButton(
                            onClick = { expandedColor = true }
                        ) {
                            Icon(
                                imageVector = Icons.Filled.KeyboardArrowDown,
                                contentDescription = null
                            )
                        }
                        DropdownMenu(
                            expanded = expandedColor,
                            onDismissRequest = { expandedColor = false },
                            modifier = Modifier
                                .width(330.dp)
                                .border(0.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(
                                    roundDp
                                )),
                            containerColor = MaterialTheme.colorScheme.primary,
                            shape = RoundedCornerShape(roundDp)
                        ) {
                            val groupColors = colorOptions.chunked(4)
                            groupColors.forEach { colorRows ->
                                DropdownMenuItem(
                                    text = {
                                        Row(
                                            modifier = Modifier.fillMaxWidth(),
                                            horizontalArrangement = Arrangement.SpaceBetween,
                                            verticalAlignment = Alignment.CenterVertically
                                        ) {
                                            colorRows.forEach { color ->
                                                Box(
                                                    modifier = Modifier
                                                        .size(34.dp, 34.dp)
                                                        .background(color,
                                                            shape = RoundedCornerShape(50)
                                                        )
                                                        .border(
                                                            1.dp,
                                                            MaterialTheme.colorScheme.secondary,
                                                            RoundedCornerShape(50)
                                                        )
                                                        .clickable {
                                                            selectedColor = color
                                                            expandedColor = false
                                                        }
                                                )
                                            }
                                        }
                                    },
                                    onClick = {
                                        // Leave this to keep DropDownMenuItem happy
                                    }
                                )
                            }
                        }
                    }
                }
            }
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
                    //NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Add(modifier = Modifier.padding(innerPadding))
        }
    }
}