package com.marshall.data.local.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "crypto_currencies")
data class CryptoEntity(
    @PrimaryKey val symbol: String,
    val baseAsset: String,
    val quoteAsset: String,
    val openPrice: String,
    val lowPrice: String,
    val highPrice: String,
    val lastPrice: String,
    val volume: String,
    val bidPrice: String,
    val askPrice: String,
    val at: Long
)