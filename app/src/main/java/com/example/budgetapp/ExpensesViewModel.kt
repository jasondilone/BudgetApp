package com.example.budgetapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ExpensesViewModel(application: Application) : AndroidViewModel(application) {
    private val repository: ExpensesRepository =
        OfflineExpensesRepository(BudgetDatabase.getDatabase(application).expenseDao())

    val allExpenses = repository.getAllExpensesStream()

    fun deleteExpense(expense: Expense) = viewModelScope.launch {
        repository.deleteExpense(expense)
    }

    fun clearAllExpenses() = viewModelScope.launch {
        repository.clearAll()
    }

    fun insertExpense(expense: Expense) = viewModelScope.launch {
        repository.insertExpense(expense)
    }

    fun updateExpense(expense: Expense) = viewModelScope.launch {
        repository.updateExpense(expense)
    }
}

