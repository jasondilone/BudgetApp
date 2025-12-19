package com.example.budgetapp.data.repository

import com.example.budgetapp.data.dao.CategoryDao
import com.example.budgetapp.data.dao.ExpenseDao
import com.example.budgetapp.data.dao.SettingsDao
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.data.entity.Settings
import kotlinx.coroutines.flow.Flow

class BudgetRepository(
    private val expenseDao: ExpenseDao,
    private val categoryDao: CategoryDao,
    private val settingsDao: SettingsDao
) {

    // Expenses
    fun getExpense(id: Long): Flow<Expense?> =
        expenseDao.getExpense(id)
    fun getAllExpenses(): Flow<List<Expense>> =
        expenseDao.getAllExpenses()
    suspend fun addExpense(expense: Expense) =
        expenseDao.insert(expense)
    suspend fun removeExpense(expense: Expense) =
        expenseDao.delete(expense)
    fun getCentsSpentBetween(startMillis: Long, endMillis: Long): Flow<Long> =
        expenseDao.getCentsSpentBetween(startMillis, endMillis)

    // Categories
    fun getAllCategories(): Flow<List<Category>> =
        categoryDao.getAllCategories()
    suspend fun addCategory(category: Category) =
        categoryDao.insert(category)
    suspend fun removeCategory(category: Category) =
        categoryDao.delete(category)

    // Settings
    fun getSettings(): Flow<Settings?> = settingsDao.getSettings()
    fun getBudgetCents(): Flow<Long> =
        settingsDao.getBudgetCents()

    private companion object {
        const val SETTINGS_ID = 1
    }
    suspend fun updateBudget(newBudgetCents: Long) {
        settingsDao.insert(Settings(id = SETTINGS_ID, budgetCents = newBudgetCents))
    }
}