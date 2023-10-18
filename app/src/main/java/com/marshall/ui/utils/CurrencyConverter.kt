package com.marshall.ui.utils


data class Currency(
    val code: String,
    val conversionRate: Double
)

object Currencies {
    val INR = Currency("₹ INR", 1.0)
    val USD = Currency("$ USD", 0.01201111)
    val SEK = Currency("SEK", 0.13110712)
}

fun convertToSelectedCurrency(amountInInr: Double, selectedCurrency: Currency): Double {
    val rate = selectedCurrency.conversionRate
    return amountInInr * rate
}

