package com.example.budgetapp

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "expenses")
data class Expense(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val category: String,
    val description: String,
    val amount: Double,
    val date: String,
    val isRecurring: Boolean = false
)
