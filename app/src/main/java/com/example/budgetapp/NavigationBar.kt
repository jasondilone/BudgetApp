package com.example.budgetapp

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.budgetapp.theme.BudgetAppTheme

@Composable
fun NavigationBar() {
    var selectedIcon = remember { mutableStateOf(NavItem.Home) }
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(roundDp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceBetween
    ) {

        // Home Icon
        IconButton(
            onClick = {
                selectedIcon.value = NavItem.Home
            },
            modifier = Modifier
                .background(
                    if (selectedIcon.value == NavItem.Home) MaterialTheme.colorScheme.secondary
                    else Color.Transparent,
                    CircleShape
                )
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Home,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (selectedIcon.value == NavItem.Home) MaterialTheme.colorScheme.onSecondary
                else MaterialTheme.colorScheme.onPrimary
            )
        }

        // List Icon
        IconButton(
            onClick = {
                selectedIcon.value = NavItem.Expenses
            },
            modifier = Modifier
                .background(
                    if (selectedIcon.value == NavItem.Expenses) MaterialTheme.colorScheme.secondary
                    else Color.Transparent,
                    CircleShape
                )
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.List,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (selectedIcon.value == NavItem.Expenses) MaterialTheme.colorScheme.onSecondary
                else MaterialTheme.colorScheme.onPrimary
            )
        }

        // Add Icon
        IconButton(
            onClick = {
                selectedIcon.value = NavItem.Add
            },
            modifier = Modifier
                .background(
                    if (selectedIcon.value == NavItem.Add) MaterialTheme.colorScheme.secondary
                    else Color.Transparent,
                    CircleShape
                )
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Add,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (selectedIcon.value == NavItem.Add) MaterialTheme.colorScheme.onSecondary
                else MaterialTheme.colorScheme.onPrimary
            )
        }

        // Settings Icon
        IconButton(
            onClick = {
                selectedIcon.value = NavItem.Settings
            },
            modifier = Modifier
                .background(
                    if (selectedIcon.value == NavItem.Settings) MaterialTheme.colorScheme.secondary
                    else Color.Transparent,
                    CircleShape
                )
                .size(50.dp)
        ) {
            Icon(
                imageVector = Icons.Outlined.Settings,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = if (selectedIcon.value == NavItem.Settings) MaterialTheme.colorScheme.onSecondary
                else MaterialTheme.colorScheme.onPrimary
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun NavigationBarPreview() {
    BudgetAppTheme(true) {
        NavigationBar()
    }
}