package com.example.budgetapp.data

import java.time.LocalDate

data class Expense(
    val id: Int,
    val category: String,
    val description: String,
    val amount: Double,
    val date: LocalDate,
    val isRecurring: Boolean
)