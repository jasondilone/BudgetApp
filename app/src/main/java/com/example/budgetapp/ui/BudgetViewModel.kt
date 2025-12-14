package com.example.budgetapp.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.repository.BudgetRepository
import com.example.budgetapp.data.entity.Expense
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

enum class BudgetPeriod { WEEKLY, MONTHLY }
class BudgetViewModel(
    private val repo: BudgetRepository
) : ViewModel() {
    val expenses = repo.getAllExpenses()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())
    val categories = repo.getAllCategories()
        .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), emptyList())


    val budgetCents: StateFlow<Long> =
        repo.getBudgetCents()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(5_000), 0L)
    fun deleteExpense(expense: Expense) {
        viewModelScope.launch {
            repo.removeExpense(expense)
        }
    }
    fun updateBudgetCents(newBudgetCents: Long) {
        viewModelScope.launch {
            repo.updateBudget(newBudgetCents)
        }
    }
}