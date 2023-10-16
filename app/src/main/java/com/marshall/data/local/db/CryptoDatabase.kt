package com.marshall.data.local.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = [CryptoEntity::class], version = 1)
abstract class CryptoDatabase : RoomDatabase() {
    abstract fun cryptoCurrencyDao(): CryptoCurrencyDao
}



