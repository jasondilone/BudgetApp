package com.example.budgetapp

import kotlinx.coroutines.flow.Flow

interface ExpensesRepository {
    fun getAllExpensesStream(): Flow<List<Expense>>
    fun getExpenseStream(id: Int): Flow<Expense?>
    suspend fun insertExpense(expense: Expense)
    suspend fun updateExpense(expense: Expense)
    suspend fun deleteExpense(expense: Expense)
    suspend fun clearAll()
}

class OfflineExpensesRepository(private val expenseDao: ExpenseDao) : ExpensesRepository {
    override fun getAllExpensesStream(): Flow<List<Expense>> = expenseDao.getAllExpensesStream()
    override fun getExpenseStream(id: Int): Flow<Expense?> = expenseDao.getExpenseStream(id)
    override suspend fun insertExpense(expense: Expense) = expenseDao.insertExpense(expense)
    override suspend fun updateExpense(expense: Expense) = expenseDao.updateExpense(expense)
    override suspend fun deleteExpense(expense: Expense) = expenseDao.deleteExpense(expense)
    override suspend fun clearAll() = expenseDao.clearAll()
}
