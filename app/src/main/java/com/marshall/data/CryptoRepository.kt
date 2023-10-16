package com.marshall.data

import com.marshall.data.local.CryptoLocalDataSource
import com.marshall.data.remote.CryptoRemoteDataSource
import com.marshall.data.remote.RemoteCryptoCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class CryptoRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoLocalDataSource
) {
    fun getAllCryptoCurrencies(): Flow<List<CryptoCurrency>> {
        return flow {
            val cryptosList = remoteDataSource.getAllCryptoCurrencies()
            emit(mapToData(cryptosList))
        }
    }
}

private fun mapToData(cryptoList: List<RemoteCryptoCurrency>): List<CryptoCurrency> =
    cryptoList.map {
        CryptoCurrency(
            symbol = it.symbol,
            price = it.lastPrice,
            volume = it.volume,
            at = it.at
        )
    }

