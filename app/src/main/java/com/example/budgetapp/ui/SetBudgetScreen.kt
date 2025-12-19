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
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.roundDp
import com.example.budgetapp.theme.BudgetAppTheme

@Composable
fun SetBudget(modifier: Modifier = Modifier) {
    var expandedTimeSelection by remember { mutableStateOf(false) }

    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp),
        verticalArrangement = Arrangement.Top
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            Icon(
                imageVector = Icons.Filled.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.rotate(90f)
                    .align(Alignment.CenterVertically)
                    .padding(horizontal = 10.dp)
                    .clickable {  }
            )
            Text(
                text = stringResource(R.string.set_budget),
                fontSize = largeFontSize
            )

        }

        Spacer(modifier = Modifier.height(20.dp))

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

        // Category
        Text(
            text = stringResource(R.string.time),
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
                            text = stringResource(R.string.monthly),
                            fontSize = mediumFontSize,
                            color = MaterialTheme.colorScheme.tertiary,
                            maxLines = 1,
                            modifier = Modifier.padding(start = 8.dp)
                        )
                    }
                }

                // Dropdown
                IconButton(
                    onClick = { expandedTimeSelection = true }
                ) {
                    Icon(
                        imageVector = Icons.Filled.KeyboardArrowDown,
                        contentDescription = null
                    )
                }
                DropdownMenu(
                    expanded = expandedTimeSelection,
                    onDismissRequest = { expandedTimeSelection = false },
                    modifier = Modifier
                        .width(330.dp)
                        .border(2.dp, MaterialTheme.colorScheme.onPrimary, RoundedCornerShape(
                            roundDp
                        ))
                        .background(MaterialTheme.colorScheme.tertiary, RoundedCornerShape(roundDp))
                ) {

                }
            }
        }

        // "Set" button
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
                    text = stringResource(R.string.set),
                    fontSize = mediumFontSize,
                    color = MaterialTheme.colorScheme.onSecondary
                )
            }
        }
    }
}

@Preview
@Composable
fun SetBudgetPreview() {
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
            SetBudget(modifier = Modifier.padding(innerPadding))
        }
    }
}