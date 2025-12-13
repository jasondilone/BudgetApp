package com.example.budgetapp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class AddExpenseViewModelFactory(
    private val repository: ExpensesRepository
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(AddExpenseViewModel::class.java)) {
            @Suppress("UNCHECKED_CAST")
            return AddExpenseViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
