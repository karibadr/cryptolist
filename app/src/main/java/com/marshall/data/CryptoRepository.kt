package com.marshall.data

import com.marshall.data.remote.CryptoRemoteDataSource
import com.marshall.data.remote.RemoteCryptoCurrency
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CryptoRepository(
    private val remoteDataSource: CryptoRemoteDataSource = CryptoRemoteDataSource()
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

