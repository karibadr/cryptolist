package com.marshall

import com.marshall.data.local.db.CryptoEntity
import com.marshall.data.remote.RemoteCryptoCurrency

object TestingData {
    fun remoteCryptoCurrencies() = listOf(
        RemoteCryptoCurrency(
            symbol = "btcinr",
            baseAsset = "btc",
            quoteAsset = "inr",
            openPrice = "2336000",
            lowPrice = "2306001.0",
            highPrice = "2337996.0",
            lastPrice = "2307745.0",
            volume = "2.67978",
            bidPrice = "2307746.0",
            askPrice = "2314847.0",
            at = 1697382332000
        ),
        RemoteCryptoCurrency(
            symbol = "xrpinr",
            baseAsset = "xrp",
            quoteAsset = "inr",
            openPrice = "42.1041",
            lowPrice = "41.5021",
            highPrice = "42.4419",
            lastPrice = "41.778",
            volume = "91730.8",
            bidPrice = "41.776",
            askPrice = "41.7779",
            at = 1697382332000
        ),
        RemoteCryptoCurrency(
            symbol = "ethinr",
            baseAsset = "eth",
            quoteAsset = "inr",
            openPrice = "133300",
            lowPrice = "133200.1",
            highPrice = "135957.3",
            lastPrice = "133200.1",
            volume = "13.0138",
            bidPrice = "133200.1",
            askPrice = "133541.6",
            at = 1697382332000
        ),
        RemoteCryptoCurrency(
            symbol = "trxinr",
            baseAsset = "trx",
            quoteAsset = "inr",
            openPrice = "7.3465",
            lowPrice = "7.2603",
            highPrice = "7.486",
            lastPrice = "7.2779",
            volume = "436858.0",
            bidPrice = "7.2749",
            askPrice = "7.2832",
            at = 1697382332000
        )
    )

    fun localCryptoCurrencies() = listOf(
        CryptoEntity(
            symbol = "btcinr",
            baseAsset = "btc",
            quoteAsset = "inr",
            openPrice = "2336000",
            lowPrice = "2306001.0",
            highPrice = "2337996.0",
            lastPrice = "2307745.0",
            volume = "2.67978",
            bidPrice = "2307746.0",
            askPrice = "2314847.0",
            at = 1697382332000
        ),
        CryptoEntity(
            symbol = "xrpinr",
            baseAsset = "xrp",
            quoteAsset = "inr",
            openPrice = "42.1041",
            lowPrice = "41.5021",
            highPrice = "42.4419",
            lastPrice = "41.778",
            volume = "91730.8",
            bidPrice = "41.776",
            askPrice = "41.7779",
            at = 1697382332000
        ),
        CryptoEntity(
            symbol = "ethinr",
            baseAsset = "eth",
            quoteAsset = "inr",
            openPrice = "133300",
            lowPrice = "133200.1",
            highPrice = "135957.3",
            lastPrice = "133200.1",
            volume = "13.0138",
            bidPrice = "133200.1",
            askPrice = "133541.6",
            at = 1697382332000
        ),
        CryptoEntity(
            symbol = "trxinr",
            baseAsset = "trx",
            quoteAsset = "inr",
            openPrice = "7.3465",
            lowPrice = "7.2603",
            highPrice = "7.486",
            lastPrice = "7.2779",
            volume = "436858.0",
            bidPrice = "7.2749",
            askPrice = "7.2832",
            at = 1697382332000
        )
    )
}