package com.marshall.data.local.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface CryptoCurrencyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(currencies: List<CryptoEntity>)

    @Query("SELECT * FROM crypto_currencies")
    fun getAllCryptos(): Flow<List<CryptoEntity>>

    @Query("DELETE FROM crypto_currencies")
    suspend fun deleteAll()
}
