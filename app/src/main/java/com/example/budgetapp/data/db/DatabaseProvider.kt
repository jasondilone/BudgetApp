package com.example.budgetapp.data.db

import android.content.Context
import androidx.room.Room

object DatabaseProvider {
    @Volatile
    private var INSTANCE: BudgetAppDatabase? = null

    fun getDatabase(context: Context): BudgetAppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                BudgetAppDatabase::class.java,
                "budget_app_database"
            )
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
        }
    }
}