package com.example.budgetapp.data.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.data.dao.CategoryDao
import com.example.budgetapp.data.entity.Expense
import com.example.budgetapp.data.dao.ExpenseDao
import com.example.budgetapp.data.entity.Settings
import com.example.budgetapp.data.dao.SettingsDao

@Database(
    entities = [
        Category::class,
        Expense::class,
        Settings::class
    ],
    version = 2,
    exportSchema = true
)
abstract class BudgetAppDatabase: RoomDatabase() {
    abstract fun expenseDao(): ExpenseDao
    abstract fun categoryDao(): CategoryDao
    abstract fun settingsDao(): SettingsDao
}
