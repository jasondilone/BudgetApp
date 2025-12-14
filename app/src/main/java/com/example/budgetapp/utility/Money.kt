package com.example.budgetapp.utility

import java.text.DecimalFormat

private val moneyFormatter = DecimalFormat("#,##0.00")

fun formatCents(amountCents: Long): String =
    "$" + moneyFormatter.format(amountCents / 100.0)