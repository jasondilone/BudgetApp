package com.example.budgetapp

import java.time.LocalDate

data class Expense(
    val category: String,
    val description: String,
    val amount: Double,
    val date: LocalDate,
    val isRecurring: Boolean
)
