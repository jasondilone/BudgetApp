package com.example.budgetapp

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

import com.example.budgetapp.ui.BottomNavigationBar





@Composable
fun Settings() {
    Scaffold(
        bottomBar = {BottomNavigationBar(4)}
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(all = 8.dp)
                .padding(innerPadding)
        ) {

        }
    }
}

@Preview(showBackground = true)
@Composable
fun SettingsPreview() {
    Settings()
}