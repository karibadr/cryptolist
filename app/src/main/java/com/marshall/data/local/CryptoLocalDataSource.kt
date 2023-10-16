package com.marshall.data.local

import com.marshall.data.local.db.CryptoDatabase
import com.marshall.data.local.db.CryptoEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CryptoLocalDataSource @Inject constructor(database: CryptoDatabase) {

    private val cryptoDAO = database.cryptoCurrencyDao()

    suspend fun getCryptos(): Flow<List<CryptoEntity?>> =
        cryptoDAO.getAllCryptos()


    suspend fun updateCryptos(list: List<CryptoEntity>) = cryptoDAO.insertAll(list)

}