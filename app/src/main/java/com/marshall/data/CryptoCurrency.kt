package com.marshall.data

data class CryptoCurrency(
    val symbol: String,
    val price: String,
    val volume: String,
    val at: Long
)

/**
 * A wrapper interface that encapsulates different responses type
 */
sealed interface Results<T> {
    data class Success<T>(val data: T) : Results<T>
    class Error<T> : Results<T>
    class Loading<T> : Results<T>
    class Empty<T> : Results<T>
}