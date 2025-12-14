package com.example.budgetapp.ui

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.data.repository.BudgetRepository
import com.example.budgetapp.data.entity.Expense
import kotlinx.coroutines.launch

class AddScreenViewModel(private val budgetRepository: BudgetRepository) : ViewModel() {
    var expenseUiState by mutableStateOf(ExpenseUiState())
        private set
    fun updateUiState(expenseDetails: ExpenseDetails) {
        expenseUiState =
            ExpenseUiState(expenseDetails = expenseDetails, isEntryValid = validateInput(expenseDetails))
    }
    fun saveExpense() {
        if (!expenseUiState.isEntryValid) return
        viewModelScope.launch {
            budgetRepository.addExpense(expenseUiState.expenseDetails.toExpense())
        }
    }
    private fun validateInput(uiState: ExpenseDetails = expenseUiState.expenseDetails): Boolean {
        return with(uiState) {
            description.isNotBlank() &&
                    amountCents > 0 &&
                    dateEpochMillis > 0
        }
        }
    fun saveCategory(selectedColor: Color) {
        val name = expenseUiState.expenseDetails.description.trim()
        if (name.isBlank()) return

        viewModelScope.launch {
            budgetRepository.addCategory(
                Category(
                    name = name,
                    color = selectedColor.toArgb()
                )
            )
        }
    }
}

data class ExpenseUiState(
    val expenseDetails: ExpenseDetails = ExpenseDetails(),
    val isEntryValid: Boolean = false
)
data class ExpenseDetails(
    val id: Long = 0,
    val categoryId: Long? = null,
    val description: String = "",
    val amountCents: Long = 0,
    val dateEpochMillis: Long = 0,
    val isRecurring: Boolean = false
)
fun ExpenseDetails.toExpense(): Expense = Expense(
    id = id,
    categoryId = categoryId,
    description = description,
    amountCents = amountCents,
    dateEpochMillis = dateEpochMillis,
    isRecurring = isRecurring
)
