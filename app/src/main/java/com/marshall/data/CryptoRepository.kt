package com.marshall.data

import com.marshall.data.local.CryptoLocalDataSource
import com.marshall.data.local.db.CryptoEntity
import com.marshall.data.remote.CryptoRemoteDataSource
import com.marshall.data.remote.RemoteCryptoCurrency
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject


class CryptoRepository @Inject constructor(
    private val remoteDataSource: CryptoRemoteDataSource,
    private val localDataSource: CryptoLocalDataSource
) {
    fun getAllCryptoCurrencies(isRefresh: Boolean = false): Flow<Results<List<CryptoCurrency>>> =
        flow {
            if (isRefresh || localDataSource.getCryptos().first().isEmpty()) {
                val cryptosList = remoteDataSource.getAllCryptoCurrencies()
                if (cryptosList.isEmpty()) {
                    emit(Results.Empty())
                } else {
                    localDataSource.updateCryptos(cryptosList.map { it.toLocalData() })
                    emit(Results.Success(cryptosList.map { it.fromRemoteData() }))
                }
            } else {
                emit(Results.Success(localDataSource.getCryptos().first().map { it.toData() }))
            }
        }.onStart {
            emit(Results.Loading())
        }.catch {
            emit(Results.Error())
        }.flowOn(Dispatchers.IO)
}

private fun CryptoEntity.toData() = CryptoCurrency(
    symbol = this.symbol,
    price = this.lastPrice,
    volume = this.volume,
    at = this.at
)

private fun RemoteCryptoCurrency.toLocalData() = CryptoEntity(
    symbol = this.symbol,
    baseAsset = this.baseAsset,
    quoteAsset = this.quoteAsset,
    openPrice = this.openPrice,
    lowPrice = this.lowPrice,
    highPrice = this.highPrice,
    lastPrice = this.lastPrice,
    volume = this.volume,
    bidPrice = this.bidPrice,
    askPrice = this.askPrice,
    at = this.at
)

private fun RemoteCryptoCurrency.fromRemoteData() = CryptoCurrency(
    symbol = this.symbol,
    price = this.lastPrice,
    volume = this.volume,
    at = this.at
)



