package com.example.budgetapp


import android.content.Context
import androidx.room.Room

interface AppContainer {
    val expensesRepository: ExpensesRepository
}

class AppDataContainer(private val context: Context) : AppContainer {
    override val expensesRepository: ExpensesRepository by lazy {
        OfflineExpensesRepository(
            Room.databaseBuilder(
                context,
                BudgetDatabase::class.java,
                "budget_database"
            ).build().expenseDao()
        )
    }
}
