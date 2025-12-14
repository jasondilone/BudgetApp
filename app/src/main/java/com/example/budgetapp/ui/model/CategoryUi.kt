package com.example.budgetapp.ui.model

import androidx.compose.ui.graphics.Color
import com.example.budgetapp.data.entity.Category
import com.example.budgetapp.ui.CategoryUi

data class CategoryUi(
    val name: String,
    val color: Color
)

fun Category.toUi(): CategoryUi =
    CategoryUi(
        name = name,
        color = Color(color)
    )