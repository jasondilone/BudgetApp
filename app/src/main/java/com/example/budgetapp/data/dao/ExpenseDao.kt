package com.example.budgetapp.data.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.budgetapp.data.entity.Expense
import kotlinx.coroutines.flow.Flow

@Dao
interface ExpenseDao {
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun insert(expense: Expense)
    @Delete
    suspend fun delete(expense: Expense)
    @Query("SELECT * FROM expenses WHERE id = :id")
    fun getExpense(id: Long): Flow<Expense>
    @Query("SELECT * FROM expenses ORDER BY dateEpochMillis DESC")
    fun getAllExpenses(): Flow<List<Expense>>
    @Query(
        """
        SELECT SUM(amountCents) 
        FROM expenses 
        WHERE dateEpochMillis BETWEEN :startMillis AND :endMillis
        """)
    fun getCentsSpentBetween(startMillis: Long, endMillis: Long): Flow<Long>
}