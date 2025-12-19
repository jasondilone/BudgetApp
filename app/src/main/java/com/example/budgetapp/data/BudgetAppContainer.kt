package com.example.budgetapp.data

import android.content.Context
import com.example.budgetapp.data.db.DatabaseProvider
import com.example.budgetapp.data.repository.BudgetRepository

interface BudgetAppContainer {
    val budgetRepository: BudgetRepository
}

class BudgetAppDataContainer(private val context: Context) : BudgetAppContainer {
    override val budgetRepository: BudgetRepository by lazy {
        val db = DatabaseProvider.getDatabase(context)
        BudgetRepository(
            db.expenseDao(),
            db.categoryDao(),
            db.settingsDao()
        )
    }
}