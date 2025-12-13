package com.example.budgetapp

import androidx.contentpager.content.Query
import androidx.room.*
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @androidx.room.Query("SELECT * FROM expenses ORDER BY date DESC")
    fun getAllExpensesStream(): Flow<List<Expense>>

    @androidx.room.Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpenseStream(id: Int): Flow<Expense?>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertExpense(expense: Expense)

    @Update
    suspend fun updateExpense(expense: Expense)

    @Delete
    suspend fun deleteExpense(expense: Expense)

    @androidx.room.Query("DELETE FROM expenses")
    suspend fun clearAll()
}
