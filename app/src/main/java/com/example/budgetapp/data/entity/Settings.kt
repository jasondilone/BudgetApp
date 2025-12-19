package com.example.budgetapp.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity("settings")
data class Settings(
    @PrimaryKey
    val id: Int = 1,
    val budgetCents: Long = 0
)
