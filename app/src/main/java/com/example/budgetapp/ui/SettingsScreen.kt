package com.example.budgetapp.ui

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
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.rotate
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.R
import com.example.budgetapp.largeFontSize
import com.example.budgetapp.mediumFontSize
import com.example.budgetapp.theme.BudgetAppTheme

@Composable
fun Settings(
    modifier: Modifier = Modifier,
    isDarkMode: Boolean,
    onToggleDarkMode: (Boolean) -> Unit
) {
    Column(
        modifier = modifier.fillMaxSize().padding(top = 25.dp)
            .background(color = MaterialTheme.colorScheme.background),
        verticalArrangement = Arrangement.Top
    ) {
        Text(
            text = stringResource(R.string.settings),
            fontSize = largeFontSize,
            modifier = Modifier.padding(horizontal = 10.dp)
        )

        Spacer(modifier = Modifier.padding(top = 20.dp))

        // Set budget
        Card(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable {  },
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(R.string.set_budget),
                    fontSize = mediumFontSize,
                    modifier = Modifier.padding(16.dp)
                        .padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(270f)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 30.dp)
                )
            }
        }

        // Recurring
        Card(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min)
                .clickable{  },
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(R.string.recurring),
                    fontSize = mediumFontSize,
                    modifier = Modifier.padding(16.dp)
                        .padding(start = 10.dp)
                )
                Spacer(modifier = Modifier.weight(1f))
                Icon(
                    imageVector = Icons.Filled.KeyboardArrowDown,
                    contentDescription = null,
                    modifier = Modifier.rotate(270f)
                        .align(Alignment.CenterVertically)
                        .padding(horizontal = 30.dp)
                )
            }
        }

        // Dark mode
        Card(
            modifier = Modifier.padding(horizontal = 10.dp, vertical = 5.dp)
                .fillMaxWidth()
                .height(IntrinsicSize.Min),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.primary)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    stringResource(R.string.dark_mode),
                    fontSize = mediumFontSize,
                    modifier = Modifier.padding(16.dp)
                        .padding(start = 10.dp)
                )

                Spacer(modifier = Modifier.weight(1f))
                Switch(
                    checked = isDarkMode,
                    onCheckedChange = { checked ->
                        onToggleDarkMode(checked)
                    },
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
    }
}

@Preview
@Composable
fun SettingsPreview() {
    BudgetAppTheme(darkTheme = false) {
        Scaffold(
            bottomBar = {
                Box(
                    modifier = Modifier.padding(horizontal = 20.dp)
                        .padding(top = 8.dp, bottom = 32.dp)
                ) {
                    NavigationBarPreview()
                }
            }
        ) { innerPadding ->
            Settings(
                modifier = Modifier.padding(innerPadding),
                isDarkMode = false,
                onToggleDarkMode = {  }
            )
        }
    }
}