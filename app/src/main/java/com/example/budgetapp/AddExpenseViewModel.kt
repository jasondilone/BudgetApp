package com.example.budgetapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddExpenseViewModel(private val repository: ExpensesRepository) : ViewModel() {

    fun addExpense(
        category: String,
        description: String,
        amount: Double,
        isRecurring: Boolean
    ) {
        val expense = Expense(
            category = category,
            description = description,
            amount = amount,
            date = LocalDate.now().toString(),
            isRecurring = isRecurring
        )
        viewModelScope.launch {
            repository.insertExpense(expense)
        }
    }
}
