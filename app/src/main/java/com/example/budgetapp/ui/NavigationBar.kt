package com.example.budgetapp.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material.icons.outlined.Add
import androidx.compose.material.icons.outlined.Home
import androidx.compose.material.icons.outlined.List
import androidx.compose.material.icons.outlined.Settings
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.budgetapp.roundDp
import com.example.budgetapp.theme.BudgetAppTheme
@Composable //navigation functionality -----------------------------------------
fun NavigationBar(navController: NavController) {
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry?.destination?.route

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = MaterialTheme.colorScheme.primary, RoundedCornerShape(roundDp))
            .padding(10.dp),
        horizontalArrangement = Arrangement.SpaceEvenly
    ) {
        NavIcon(Icons.Default.Home, "home", currentRoute, navController)
        NavIcon(Icons.Default.List, "expenses", currentRoute, navController)
        NavIcon(Icons.Default.Add, "add", currentRoute, navController)
        NavIcon(Icons.Default.Settings, "settings", currentRoute, navController)
    }
}
@Composable
fun NavIcon(
    icon: ImageVector,
    route: String,
    currentRoute: String?,
    navController: NavController
) {
    val isSelected = currentRoute == route

    IconButton(
        onClick = { if (!isSelected) navController.navigate(route) },
        modifier = Modifier
            .background(
                if (isSelected) MaterialTheme.colorScheme.secondary else Color.Transparent,
                CircleShape
            )
            .size(50.dp)
    ) {
        Icon(
            imageVector = icon,
            contentDescription = route,
            modifier = Modifier.size(30.dp),
            tint = if (isSelected) MaterialTheme.colorScheme.onSecondary
            else MaterialTheme.colorScheme.onPrimary
        )
    }
}

// ui preview --------------------------------------------------
@Composable
fun NavigationBarPreview(navController: NavHostController) {
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
        NavigationBarPreview()
    }
}