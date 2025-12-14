package com.example.budgetapp.data.db

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.example.budgetapp.data.entity.Settings
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

object DatabaseProvider {
    @Volatile
    private var INSTANCE: BudgetAppDatabase? = null

    private const val DEFAULT_BUDGET_CENTS = 1000_00L

    fun getDatabase(context: Context): BudgetAppDatabase {
        return INSTANCE ?: synchronized(this) {
            INSTANCE ?: Room.databaseBuilder(
                context.applicationContext,
                BudgetAppDatabase::class.java,
                "budget_app_database"
            )
                .addCallback(object : RoomDatabase.Callback() {
                override fun onCreate(db: SupportSQLiteDatabase) {
                    super.onCreate(db)
                    CoroutineScope(Dispatchers.IO).launch {
                        getDatabase(context).settingsDao().insert(
                            Settings(id = 1, budgetCents = DEFAULT_BUDGET_CENTS)
                        )
                    }
                }
            })
                .fallbackToDestructiveMigration()
                .build()
                .also { INSTANCE = it }
        }
    }
}