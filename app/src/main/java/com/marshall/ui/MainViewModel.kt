package com.marshall.ui

import androidx.lifecycle.ViewModel
import com.marshall.data.CryptoRepository

class MainViewModel(private val cryptoRepository: CryptoRepository = CryptoRepository()) :
    ViewModel() {
    fun getAllCryptoCurrencies() =
        cryptoRepository.getAllCryptoCurrencies()

}